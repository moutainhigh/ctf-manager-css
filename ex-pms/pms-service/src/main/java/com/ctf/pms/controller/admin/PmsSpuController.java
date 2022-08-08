package com.ctf.pms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.pms.pojo.form.PmsSpuForm;
import com.ctf.pms.pojo.query.SpuPageQuery;
import com.ctf.pms.pojo.vo.PmsSpuDetailVO;
import com.ctf.pms.pojo.vo.PmsSpuPageVO;
import com.ctf.pms.service.IPmsSpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 「管理端」商品管理控制器
 *
 * @author H.m
 * @date 2021/1/4
 **/
@Api(tags = "「管理端」商品管理")
@RestController
@RequestMapping("/api/v1/spu")
@AllArgsConstructor
public class PmsSpuController {

    private IPmsSpuService pmsSpuServiced;

    @ApiOperation(value = "商品分页列表")
    @GetMapping("/pages")
    public PageResult listPmsSpuPages(SpuPageQuery queryParams) {
        IPage<PmsSpuPageVO> result = pmsSpuServiced.listPmsSpuPages(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "商品详情")
    @GetMapping("/{id}")
    public Result detail( @ApiParam("商品ID") @PathVariable Long id) {
        PmsSpuDetailVO pmsSpuDetailVO = pmsSpuServiced.getPmsSpuDetail(id);
        return Result.success(pmsSpuDetailVO);
    }

    @ApiOperation(value = "新增商品")
    @PostMapping
    public Result addSpu(@RequestBody PmsSpuForm formData) {
        boolean result = pmsSpuServiced.addSpu(formData);
        return Result.judge(result);
    }

    @ApiOperation(value = "修改商品")
    @PutMapping(value = "/{id}")
    public Result updateSpuById(
            @ApiParam("商品ID") @PathVariable Long id,
            @RequestBody PmsSpuForm formData
    ) {
        boolean result = pmsSpuServiced.updateSpuById(id,formData);
        return Result.judge(result);
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/{ids}")
    public Result delete(@ApiParam("商品ID,多个以英文逗号(,)分隔") @PathVariable String ids) {
        boolean result = pmsSpuServiced.removeBySpuIds(ids);
        return Result.judge(result);
    }

}
