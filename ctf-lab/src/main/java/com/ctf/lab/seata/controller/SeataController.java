package com.ctf.lab.seata.controller;

import com.ctf.common.result.Result;
import com.ctf.lab.seata.pojo.form.SeataForm;
import com.ctf.lab.seata.pojo.vo.SeataDataVO;
import com.ctf.lab.seata.service.ISeataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author H.m
 * @date 2022/8/5 10:30
 */

@Api(tags = "「实验室」Seata接口")
@RestController
@RequestMapping("/api/v1/seata")
@RequiredArgsConstructor
@Slf4j
public class SeataController {

    private final ISeataService seataService;

    @ApiOperation("订单支付")
    @PostMapping("/order/_pay")
    public Result payOrder(@RequestBody SeataForm seataForm) {

        boolean openTx = seataForm.isOpenTx();
        boolean result;
        if (openTx) {
            result = seataService.payOrderWithGlobalTx(seataForm);
        } else {
            result = seataService.payOrder(seataForm);
        }
        return Result.success(result);
    }

    @ApiOperation("获取模拟数据")
    @GetMapping("/data")
    public Result getData() {
        SeataDataVO result = seataService.getData();
        return Result.success(result);
    }

    @ApiOperation("重置模拟数据")
    @PutMapping("/data/_reset")
    public Result resetData() {
        boolean result = seataService.resetData();
        return Result.success(result);
    }

}
