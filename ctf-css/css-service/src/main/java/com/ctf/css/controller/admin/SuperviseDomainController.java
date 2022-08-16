package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.common.web.domain.Option;
import com.ctf.css.mapper.SuperviseDomainMapper;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.form.SuperviseDomainForm;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import com.ctf.css.pojo.vo.ex.UserVO;
import com.ctf.css.service.SuperviseDomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangyizheng
 * @Date 2022/8/8 11:55
 * @Describe SuperviseDomainController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-督导领域信息接口")
@RequestMapping("/store/supervise")
@RequiredArgsConstructor
public class SuperviseDomainController {
    private final SuperviseDomainService superviseDomainService;

    @ApiOperation(value = "督导领域分页列表")
    @GetMapping("/pages")
    public PageResult listAdvertPages(SupervisorDomainPageQuery queryParams) {

        // 分页查询
        Page<SuperviseDomainVo> result = superviseDomainService.pageSupervise(queryParams);
        return PageResult.success(result);
    }

//    @ApiOperation(value = "督导领域下拉列表")
//    @GetMapping("/options")
//    public Result<List<Option>> listRoleOptions() {
//        List<Option> list = sysRoleService.listRoleOptions();
//        return Result.success(list);
//    }

    @ApiOperation(value = "督导领域详情")
    @GetMapping("/{DomainId}")
    public Result getRoleDetail(
            @ApiParam("领域ID") @PathVariable Long DomainId
    ) {
        SuperviseDomain superviseDomain = superviseDomainService.getById(DomainId);
        return Result.success(superviseDomain);
    }

    @ApiOperation(value = "新增督导领域")
    @PostMapping
    public Result saveDomain(@RequestBody @Validated SuperviseDomainForm superviseDomainForm) {
        boolean result = superviseDomainService.saveSuperviseDomain(superviseDomainForm);
        return Result.judge(result);
    }

    @ApiOperation(value = "修改督导领域")
    @PutMapping(value = "/{id}")
    public Result updateDomain(@ApiParam("督导人员ID") @PathVariable Long id, @RequestBody @Validated SuperviseDomainForm superviseDomainForm) {
        boolean result = superviseDomainService.updateSuperviseDomain(id, superviseDomainForm);
        return Result.judge(result);
    }

    // TODO: 2022/8/12   暂时未考虑权限问题
    @ApiOperation(value = "删除督导领域")
    @DeleteMapping("/{superviseDomainIds}")
    public Result deleteDomain(@ApiParam("督导人员ID，多个以英文逗号(,)分割") @PathVariable String superviseDomainIds) {
        boolean result = superviseDomainService.deleteSuperviseDomain(superviseDomainIds);
        return Result.judge(result);
    }
}
