package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.common.web.security.annotation.RequirePerms;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.UserVO;
import com.ctf.css.service.TourSupervisorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangyizheng
 * @Date 2022/8/8 11:04
 * @Describe StoreInfoController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-督导人员信息接口")
@RequestMapping("/store/supervisor")
@RequiredArgsConstructor
public class TourSupervisorController {

    private final TourSupervisorService tourSupervisorService;

    @ApiOperation(value = "督导人员分页列表")
    @GetMapping("/pages")
    public PageResult listAdvertPages(TourSupervisorPageQuery queryParams) {

        // 分页查询
        Page<UserVO> result = tourSupervisorService.pageSupervisor(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "督导人员表单数据")
    @GetMapping("/{staffCode}/form_data")
    public Result<SuperviseForm> getSupervisorFormData(@ApiParam(value = "用户工号") @PathVariable String staffCode) {
        SuperviseForm userForm = tourSupervisorService.getSupervisorFormData(staffCode);
        return Result.success(userForm);
    }

    @ApiOperation(value = "新增督导人员")
    @PostMapping
    public Result saveUser(@RequestBody @Validated SuperviseForm superviseForm) {
        boolean result = tourSupervisorService.saveSupervisor(superviseForm);
        return Result.judge(result);
    }

    @ApiOperation(value = "修改督导人员")
    @PutMapping(value = "/{supervisorId}")
    public Result updateUser(@ApiParam("督导人员ID") @PathVariable Long supervisorId, @RequestBody @Validated SuperviseForm superviseForm) {
        boolean result = tourSupervisorService.updateSupervisor(supervisorId, superviseForm);
        return Result.judge(result);
    }

    // TODO: 2022/8/11    @RequirePerms(value = "sys:user:delete")暂时未考虑权限问题
    @ApiOperation(value = "删除督导人员")
    @DeleteMapping("/{ids}")
    public Result deleteUsers(@ApiParam("督导人员ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = tourSupervisorService.deleteSupervisors(ids);
        return Result.judge(result);
    }
}
