package com.ctf.auth.feign;

import com.ctf.common.BaseResult;
import com.ctf.wms.model.po.Warehouse;
import com.ctf.wms.model.vo.WmsOutboundNoticeOrderInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ciro
 * @date 2022/4/9 13:59
 * @description: wms模块
 */
@Component
@FeignClient(name = "jayud-wms-web")
public interface WmsClient {

    @PostMapping("/warehouse/selectList")
    public BaseResult<List<Warehouse>> selectWarehouseList(@RequestParam("tenantCode") String tenantCode, @RequestParam("status") Integer status);

    @GetMapping("/wmsOutboundNoticeOrderInfo/getDetailByMainOrder")
    public BaseResult<WmsOutboundNoticeOrderInfoVO> getDetailByMainOrder(@RequestParam("mainOrder") String mainOrder);

}
