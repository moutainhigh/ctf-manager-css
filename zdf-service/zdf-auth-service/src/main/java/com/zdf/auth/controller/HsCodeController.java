package com.zdf.auth.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.bo.AddHsCodeForm;
import com.zdf.auth.model.bo.DeleteForm;
import com.zdf.auth.model.bo.QueryForm;
import com.zdf.auth.model.enums.CorrespondEnum;
import com.zdf.auth.model.po.HsCode;
import com.zdf.auth.model.vo.HsCodeFormVO;
import com.zdf.auth.model.vo.HsCodeVO;
import com.zdf.auth.service.IBUnitService;
import com.zdf.auth.service.IHsCodeService;
import com.zdf.common.CommonPageResult;
import com.zdf.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 海关编码表 前端控制器
 * </p>
 *
 * @author LLJ
 * @since 2021-07-26
 */
@RestController
@RequestMapping("/hsCode")
@Api(tags = "海关编码管理")
public class HsCodeController {

    @Autowired
    private IHsCodeService hsCodeService;

    @Autowired
    private IBUnitService bUnitService;

    @ApiOperation(value = "海关编码列表")
    @PostMapping(value = "/findByPage")
    public CommonResult findByPage(@Valid @RequestBody QueryForm form) {

        if(form.getKey() != null && CorrespondEnum.getName(form.getKey()) == null){
            return CommonResult.error(444,"该条件无法搜索");
        }
        form.setKey(CorrespondEnum.getName(form.getKey()));

        List list = new ArrayList();
        //获取表头信息
        Class<HsCodeFormVO> seaOrderFormVOClass = HsCodeFormVO.class;
        Field[] declaredFields = seaOrderFormVOClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
            if (annotation != null) {
                Map map = new HashMap<>();
                map.put("key", declaredField.getName());
                map.put("name", annotation.value());
                list.add(map);
            }
        }
        Map map1 = new HashMap();
        map1.put("header", list);
        IPage<HsCodeFormVO> page = this.hsCodeService.findByPage(form);
        if (page.getRecords().size() == 0) {
            map1.put("pageInfo", new CommonPageResult(page));
        }else{
            CommonPageResult<HsCodeFormVO> pageVO = new CommonPageResult(page);
            map1.put("pageInfo", pageVO);
        }
        return CommonResult.success(map1);
    }

    @ApiOperation(value = "增加或修改信息")
    @PostMapping(value = "/saveOrUpdateHsCode")
    public CommonResult saveOrUpdateHsCode(@Valid @RequestBody AddHsCodeForm form) {

        //判断海关编码是否重复
        HsCode hsCodeByCodeNo = hsCodeService.getByCodeNo(form.getCodeNo());
        if(null != hsCodeByCodeNo){
            if(null == hsCodeByCodeNo.getId()){
                return CommonResult.error(444,"海关编码已存在");
            }
            if(!form.getId().equals(hsCodeByCodeNo.getId())){
                return CommonResult.error(444,"海关编码已存在");
            }
        }

        boolean result = hsCodeService.saveOrUpdateHsCode(form);
        if(result){
            return CommonResult.success();
        }
        return CommonResult.error(444,"添加或修改海关编码失败");

    }

    @ApiOperation(value = "根据编码id获取编码详情")
    @PostMapping(value = "/getHsCodeDetail")
    public CommonResult getHsCodeDetail(@RequestBody Map<String,Object> map) {
        Integer id = MapUtil.getInt(map, "id");
        HsCodeVO hsCodeVO = hsCodeService.getHsCodeDetail(id);
        return CommonResult.success(hsCodeVO);
    }

    @ApiOperation(value = "根据海关编码获取编码详情")
    @PostMapping(value = "/getHsCodeDetailByCodeNo")
    public CommonResult getHsCodeDetailByCodeNo(@RequestBody Map<String,Object> map) {
        String codeNo = MapUtil.getStr(map, "codeNo");
        HsCodeVO hsCodeVO = hsCodeService.getHsCodeDetailByCodeNo(codeNo);
        return CommonResult.success(hsCodeVO);
    }

    @ApiOperation(value = "删除海关编码")
    @PostMapping(value = "/deleteByIds")
    public CommonResult deleteByIds(@RequestBody DeleteForm form) {
        return this.hsCodeService.deleteByIds(form);
    }

    @ApiOperation(value = "添加或修改商品下拉列表框")
    @PostMapping(value = "/commodityCombox")
    public CommonResult commodityCombox() {
        //获取商品单位下拉
        List<String> units = bUnitService.getUnits();
        return CommonResult.success(units);
    }

}

