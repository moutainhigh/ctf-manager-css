package com.ctf.oms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyV3Result;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ctf.common.enums.BusinessTypeEnum;
import com.ctf.common.redis.BusinessNoGenerator;
import com.ctf.common.result.Result;
import com.ctf.common.web.exception.BusinessException;
import com.ctf.common.web.util.MemberUtils;
import com.ctf.oms.config.WxPayProperties;
import com.ctf.oms.dto.OrderInfoDTO;
import com.ctf.oms.enums.OrderStatusEnum;
import com.ctf.oms.enums.OrderTypeEnum;
import com.ctf.oms.enums.PayTypeEnum;
import com.ctf.oms.mapper.OrderMapper;
import com.ctf.oms.pojo.dto.CartItemDTO;
import com.ctf.oms.pojo.dto.OrderItemDTO;
import com.ctf.oms.pojo.entity.OmsOrder;
import com.ctf.oms.pojo.entity.OmsOrderItem;
import com.ctf.oms.pojo.form.OrderSubmitForm;
import com.ctf.oms.pojo.query.OrderPageQuery;
import com.ctf.oms.pojo.vo.OrderConfirmVO;
import com.ctf.oms.pojo.vo.OrderSubmitVO;
import com.ctf.oms.service.ICartService;
import com.ctf.oms.service.IOrderItemService;
import com.ctf.oms.service.IOrderService;
import com.ctf.pms.api.SkuFeignClient;
import com.ctf.pms.pojo.dto.CheckPriceDTO;
import com.ctf.pms.pojo.dto.SkuInfoDTO;
import com.ctf.pms.pojo.dto.LockStockDTO;
import com.ctf.ums.api.MemberFeignClient;
import com.ctf.ums.dto.MemberAddressDTO;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static com.ctf.oms.constant.OmsConstants.*;

/**
 * ?????????????????????
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OmsOrder> implements IOrderService {

    private final WxPayProperties wxPayProperties;
    private final ICartService cartService;
    private final IOrderItemService orderItemService;
    private final RabbitTemplate rabbitTemplate;
    private final StringRedisTemplate redisTemplate;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final MemberFeignClient memberFeignClient;
    private final BusinessNoGenerator businessNoGenerator;
    private final SkuFeignClient skuFeignClient;
    private final RedissonClient redissonClient;
    private final WxPayService wxPayService;

    /**
     * ??????????????????
     *
     * @param queryParams
     * @return
     */
    @Override
    public IPage<OmsOrder> listOrderPages(OrderPageQuery queryParams) {
        Page<OmsOrder> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        List<OmsOrder> list = this.baseMapper.listOrderPages(page, queryParams);
        page.setRecords(list);
        return page;
    }

    /**
     * ???????????? ??? ????????????????????????
     * <p>
     * ????????????????????????????????????????????????????????????????????????token
     * ??????????????????????????????????????????1??????????????????2??????????????????
     *
     * @param skuId ??????????????????????????????????????????
     * @return
     */
    @Override
    public OrderConfirmVO confirmOrder(Long skuId) {
        OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
        // ??????????????????????????????
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

        // ?????????????????????????????????
        CompletableFuture<Void> getOrderItemsFuture = CompletableFuture.runAsync(() -> {
            // ??????????????????????????????
            RequestContextHolder.setRequestAttributes(attributes);
            List<OrderItemDTO> orderItems = this.getOrderItems(skuId);
            orderConfirmVO.setOrderItems(orderItems);
        }, threadPoolExecutor);

        // ????????????????????????
        CompletableFuture<Void> getMemberAddressFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            Long memberId = MemberUtils.getMemberId();
            Result<List<MemberAddressDTO>> getMemberAddressResult = memberFeignClient.listMemberAddresses(memberId);
            List<MemberAddressDTO> memberAddresses;
            if (Result.isSuccess(getMemberAddressResult) && (memberAddresses = getMemberAddressResult.getData()) != null) {
                orderConfirmVO.setAddresses(memberAddresses);
            } else {
                orderConfirmVO.setAddresses(Collections.EMPTY_LIST);
            }
        }, threadPoolExecutor);

        // ????????????????????????????????????token,?????????????????????token????????????????????????
        CompletableFuture<Void> getOrderTokenFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            String orderToken = businessNoGenerator.generate(BusinessTypeEnum.ORDER);
            orderConfirmVO.setOrderToken(orderToken);
            redisTemplate.opsForValue().set(ORDER_TOKEN_PREFIX + orderToken, orderToken);
        }, threadPoolExecutor);

        CompletableFuture.allOf(getOrderItemsFuture, getMemberAddressFuture, getOrderTokenFuture).join();
        log.info("?????????????????????{}", orderConfirmVO);
        return orderConfirmVO;
    }

    /**
     * ????????????
     */
    @Override
    @GlobalTransactional
    public OrderSubmitVO submitOrder(OrderSubmitForm orderSubmitForm) {
        log.info("??????????????????:{}", JSONUtil.toJsonStr(orderSubmitForm));

        // ????????????????????????
        List<OrderItemDTO> orderItems = orderSubmitForm.getOrderItems();
        Assert.isTrue(CollectionUtil.isNotEmpty(orderItems), "??????????????????");

        // ????????????????????????
        String orderToken = orderSubmitForm.getOrderToken();
        Long releaseLockResult = this.redisTemplate.execute(new DefaultRedisScript<>("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Long.class), Collections.singletonList(ORDER_TOKEN_PREFIX + orderToken), orderToken);  // ????????????????????????1
        Assert.isTrue(releaseLockResult.equals(1l), "????????????????????????");

        OmsOrder order;
        try {
            // ????????????
            Long orderTotalAmount = orderSubmitForm.getTotalAmount();
            boolean checkResult = this.checkOrderPrice(orderTotalAmount, orderItems);
            Assert.isTrue(checkResult, "??????????????????????????????????????????????????????");

            // ??????????????????
            this.lockStock(orderToken, orderItems);

            // ????????????
            order = new OmsOrder().setOrderSn(orderToken) // ???orderToken?????????????????????
                    .setStatus(OrderStatusEnum.PENDING_PAYMENT.getCode()).setSourceType(OrderTypeEnum.APP.getCode()).setMemberId(MemberUtils.getMemberId()).setRemark(orderSubmitForm.getRemark()).setPayAmount(orderSubmitForm.getPayAmount()).setTotalQuantity(orderItems.stream().map(OrderItemDTO::getCount).reduce(0, Integer::sum)).setTotalAmount(orderItems.stream().map(item -> item.getPrice() * item.getCount()).reduce(0L, Long::sum));
            boolean result = this.save(order);

            // ??????????????????
            if (result) {
                List<OmsOrderItem> saveOrderItems = orderItems.stream().map(orderFormItem -> {
                    OmsOrderItem omsOrderItem = new OmsOrderItem();
                    BeanUtil.copyProperties(orderFormItem, omsOrderItem);
                    omsOrderItem.setOrderId(order.getId());
                    omsOrderItem.setTotalAmount(orderFormItem.getPrice() * orderFormItem.getCount());
                    return omsOrderItem;
                }).collect(Collectors.toList());
                result = orderItemService.saveBatch(saveOrderItems);
                if (result) {
                    // ??????????????????
                    rabbitTemplate.convertAndSend("order.exchange", "order.create.routing.key", orderToken);
                }
            }
            Assert.isTrue(result, "??????????????????");
        } catch (Exception e) {
            redisTemplate.opsForValue().set(ORDER_TOKEN_PREFIX + orderToken, orderToken);
            throw new BusinessException(e);
        }
        // ???????????????????????????
        OrderSubmitVO submitVO = new OrderSubmitVO();
        submitVO.setOrderId(order.getId());
        submitVO.setOrderSn(order.getOrderSn());
        return submitVO;
    }


    /**
     * ????????????
     */
    @Override
    @GlobalTransactional
    public <T> T pay(Long orderId, String appId, PayTypeEnum payTypeEnum) {
        OmsOrder order = this.getById(orderId);
        Assert.isTrue(order != null, "???????????????");
        Assert.isTrue(OrderStatusEnum.PENDING_PAYMENT.getCode().equals(order.getStatus()), "??????????????????????????????????????????");

        RLock lock = redissonClient.getLock(ORDER_SN_PREFIX + order.getOrderSn());
        try {

            lock.lock();
            T result;
            switch (payTypeEnum) {
                case WX_JSAPI:
                    result = (T) wxJsapiPay(appId, order);
                    break;
                default:
                    result = (T) balancePay(order);
                    break;
            }
            // ????????????
            Result<?> deductStockResult = skuFeignClient.deductStock(order.getOrderSn());
            Assert.isTrue(Result.isSuccess(deductStockResult), "????????????????????????");
            return result;
        } finally {
            //?????????
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }


    /**
     * ????????????
     *
     * @param order
     * @return
     */
    private Boolean balancePay(OmsOrder order) {
        // ????????????
        Long payAmount = order.getPayAmount();
        Result<?> deductBalanceResult = memberFeignClient.deductBalance(payAmount);
        Assert.isTrue(Result.isSuccess(deductBalanceResult), "????????????????????????");

        // ??????????????????
        order.setStatus(OrderStatusEnum.PAYED.getCode());
        order.setPayType(PayTypeEnum.BALANCE.getValue());
        order.setPayTime(new Date());
        this.updateById(order);
        // ?????????????????????????????????????????????
        cartService.removeCheckedItem();

        return Boolean.TRUE;
    }


    private WxPayUnifiedOrderV3Result.JsapiResult wxJsapiPay(String appId, OmsOrder order) {
        Long memberId = MemberUtils.getMemberId();
        Long payAmount = order.getPayAmount();
        // ???????????????outTradeNo?????????????????????
        if (PayTypeEnum.WX_JSAPI.getValue().equals(order.getPayType()) && StrUtil.isNotBlank(order.getOutTradeNo())) {
            try {
                wxPayService.closeOrderV3(order.getOutTradeNo());
            } catch (WxPayException e) {
                log.error(e.getMessage(), e);
                throw new BusinessException("??????????????????");
            }
        }
        // ??????id?????????????????????????????????????????????????????????
        String userIdFilledZero = String.format("%05d", memberId);
        String fiveDigitsUserId = userIdFilledZero.substring(userIdFilledZero.length() - 5);
        // ???????????????wxo???weixin order?????????????????????????????????????????????????????????????????????????????????????????????????????????
        // ????????????+3????????????+??????id???????????????????????????????????????<a href="https://tech.meituan.com/2016/11/18/dianping-order-db-sharding.html">????????????</a>
        String outTradeNo = "wxo_" + System.currentTimeMillis() + RandomUtil.randomNumbers(3) + fiveDigitsUserId;
        log.info("??????????????????????????????{}", outTradeNo);
        // ??????????????????
        order.setPayType(PayTypeEnum.WX_JSAPI.getValue());
        order.setOutTradeNo(outTradeNo);
        this.updateById(order);

        String memberOpenId = memberFeignClient.getMemberOpenId(memberId).getData();

        WxPayUnifiedOrderV3Request wxRequest = new WxPayUnifiedOrderV3Request().setOutTradeNo(outTradeNo).setAppid(appId).setNotifyUrl(wxPayProperties.getPayNotifyUrl()).setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(Math.toIntExact(payAmount))).setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(memberOpenId)).setDescription("??????-????????????" + order.getOrderSn());
        WxPayUnifiedOrderV3Result.JsapiResult jsapiResult;
        try {
            jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxRequest);
        } catch (WxPayException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("????????????????????????");
        }
        return jsapiResult;
    }

    @Override
    public boolean closeOrder(String orderToken) {
        log.info("?????????????????????orderToken:{}", orderToken);
        OmsOrder order = this.getOne(new LambdaQueryWrapper<OmsOrder>().eq(OmsOrder::getOrderSn, orderToken));
        if (order == null || !OrderStatusEnum.PENDING_PAYMENT.getCode().equals(order.getStatus())) {
            return false;
        }
        // ???????????????outTradeNo?????????????????????
        if (PayTypeEnum.WX_JSAPI.getValue().equals(order.getPayType()) && StrUtil.isNotBlank(order.getOutTradeNo())) {
            try {
                wxPayService.closeOrderV3(order.getOutTradeNo());
                order.setOutTradeNo(null);
            } catch (WxPayException e) {
                log.error(e.getMessage(), e);
                throw new BusinessException("??????????????????");
            }
        }
        order.setStatus(OrderStatusEnum.AUTO_CANCEL.getCode());
        return this.updateById(order);
    }

    @Override
    public boolean cancelOrder(Long id) {
        log.info("???????????????????????????ID???{}", id);
        OmsOrder order = this.getById(id);
        if (order == null) {
            throw new BusinessException("???????????????");
        }

        if (!OrderStatusEnum.PENDING_PAYMENT.getCode().equals(order.getStatus())) {
            throw new BusinessException("??????????????????????????????????????????"); // ??????????????????????????????????????????????????????????????????????????????????????????
        }
        // ???????????????outTradeNo?????????????????????
        if (PayTypeEnum.WX_JSAPI.getValue().equals(order.getPayType()) && StrUtil.isNotBlank(order.getOutTradeNo())) {
            try {
                wxPayService.closeOrderV3(order.getOutTradeNo());
                order.setOutTradeNo(null);
            } catch (WxPayException e) {
                log.error(e.getMessage(), e);
                throw new BusinessException("??????????????????");
            }
        }
        order.setStatus(OrderStatusEnum.USER_CANCEL.getCode());
        boolean result = this.updateById(order);
        if (result) {
            // ????????????????????????
            Result<?> unlockResult = skuFeignClient.unlockStock(order.getOrderSn());
            if (!Result.isSuccess(unlockResult)) {
                throw new BusinessException(unlockResult.getMsg());
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrder(Long id) {
        log.info("=======================?????????????????????ID???{}=======================", id);
        OmsOrder order = this.getById(id);
        if (order != null && !OrderStatusEnum.AUTO_CANCEL.getCode().equals(order.getStatus()) && !OrderStatusEnum.USER_CANCEL.getCode().equals(order.getStatus())) {
            throw new BusinessException("??????????????????????????????????????????????????????????????????");
        }
        return this.removeById(id);
    }


    @Override
    public void handleWxPayOrderNotify(SignatureHeader signatureHeader, String notifyData) throws WxPayException {
        log.info("??????????????????????????????");
        // ????????????????????????
        final WxPayOrderNotifyV3Result.DecryptNotifyResult result = this.wxPayService.parseOrderNotifyV3Result(notifyData, signatureHeader).getResult();
        log.debug("???????????????????????????[{}]", result.toString());
        // ?????????????????????????????????
        QueryWrapper<OmsOrder> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OmsOrder::getOutTradeNo, result.getOutTradeNo());
        OmsOrder orderDO = this.getOne(wrapper);
        // ??????????????????
        if (WxPayConstants.WxpayTradeStatus.SUCCESS.equals(result.getTradeState())) {
            orderDO.setStatus(OrderStatusEnum.PAYED.getCode());
            orderDO.setTransactionId(result.getTransactionId());
            orderDO.setPayTime(new Date());
            this.updateById(orderDO);
        }
        log.info("??????????????????");
        // ?????????????????????????????????????????????
        cartService.removeCheckedItem();
    }

    @Override
    public void handleWxPayRefundNotify(SignatureHeader signatureHeader, String notifyData) throws WxPayException {
        log.info("??????????????????????????????");
        // ????????????????????????
        final WxPayRefundNotifyV3Result.DecryptNotifyResult result = this.wxPayService.parseRefundNotifyV3Result(notifyData, signatureHeader).getResult();
        log.debug("???????????????????????????[{}]", result.toString());
        // ????????????????????????????????????
        QueryWrapper<OmsOrder> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OmsOrder::getOutTradeNo, result.getOutTradeNo());
        OmsOrder orderDO = this.getOne(wrapper);
        // ??????????????????
        if (WxPayConstants.RefundStatus.SUCCESS.equals(result.getRefundStatus())) {
            orderDO.setStatus(OrderStatusEnum.REFUNDED.getCode());
            orderDO.setRefundId(result.getRefundId());
            this.updateById(orderDO);
        }
        log.info("??????????????????");
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param orderTotalAmount ???????????????
     * @param orderItems       ??????????????????
     * @return true???????????????????????????????????????false??????????????????????????????????????????
     */
    private boolean checkOrderPrice(Long orderTotalAmount, List<OrderItemDTO> orderItems) {
        CheckPriceDTO checkPriceDTO = new CheckPriceDTO();
        List<CheckPriceDTO.CheckSku> checkSkus = orderItems.stream().map(orderFormItem -> {
            CheckPriceDTO.CheckSku checkSku = new CheckPriceDTO.CheckSku();
            checkSku.setSkuId(orderFormItem.getSkuId());
            checkSku.setCount(orderFormItem.getCount());
            return checkSku;
        }).collect(Collectors.toList());

        checkPriceDTO.setOrderTotalAmount(orderTotalAmount); // ???????????????
        checkPriceDTO.setCheckSkus(checkSkus); // ?????????????????????

        // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
        Result<Boolean> checkPriceResult = skuFeignClient.checkPrice(checkPriceDTO);

        boolean result = Result.isSuccess(checkPriceResult) && Boolean.TRUE.equals(checkPriceResult.getData());
        return result;
    }

    /**
     * ?????????????????????????????????
     * <p>
     * ???????????????????????????1??????????????????2??????????????????
     *
     * @param skuId ????????????????????????????????????????????????
     * @return
     */
    private List<OrderItemDTO> getOrderItems(Long skuId) {
        List<OrderItemDTO> orderItems;
        if (skuId != null) {  // ????????????
            orderItems = new ArrayList<>();
            SkuInfoDTO skuInfoDTO = skuFeignClient.getSkuInfo(skuId).getData();
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            BeanUtil.copyProperties(skuInfoDTO, orderItemDTO);

            orderItemDTO.setCount(1); // ??????????????????????????????1
            orderItems.add(orderItemDTO);
        } else { // ???????????????
            Long memberId = MemberUtils.getMemberId();
            log.info("????????????????????????????????????memberId:{}", memberId);
            List<CartItemDTO> cartItems = cartService.listCartItemByMemberId(memberId);
            orderItems = cartItems.stream().filter(CartItemDTO::getChecked).map(cartItem -> {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                BeanUtil.copyProperties(cartItem, orderItemDTO);
                return orderItemDTO;
            }).collect(Collectors.toList());
        }
        return orderItems;
    }

    /**
     * ??????????????????
     *
     * @param orderToken
     * @param orderItems
     */
    private void lockStock(String orderToken, List<OrderItemDTO> orderItems) {
        LockStockDTO lockStockDTO = new LockStockDTO();
        lockStockDTO.setOrderToken(orderToken);

        List<LockStockDTO.LockedSku> lockedSkuList = orderItems.stream().map(orderItem -> new LockStockDTO.LockedSku().setSkuId(orderItem.getSkuId()).setCount(orderItem.getCount())).collect(Collectors.toList());

        lockStockDTO.setLockedSkuList(lockedSkuList);
        skuFeignClient.lockStock(lockStockDTO);
    }


    /**
     * ?????????????????????????????????
     *
     * @param orderId ??????ID
     * @param status  ????????????
     * @param orderEx ??????????????????
     * @return
     */
    @Override
    @Transactional
    public boolean updateOrderStatus(Long orderId, Integer status, Boolean orderEx) {
        boolean result = this.update(new LambdaUpdateWrapper<OmsOrder>().eq(OmsOrder::getId, orderId).set(OmsOrder::getStatus, status));

        if (orderEx) {
            int i = 1 / 0;
        }
        return result;
    }

    /**
     * ??????????????????
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderInfoDTO getOrderInfo(Long orderId) {

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        OmsOrder omsOrder = this.getById(orderId);
        if (omsOrder != null) {
            BeanUtil.copyProperties(omsOrder, orderInfoDTO);
        }

        return orderInfoDTO;
    }

}
