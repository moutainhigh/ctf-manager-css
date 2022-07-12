package com.zdf.auth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zdf.auth.model.bo.DeleteForm;
import com.zdf.auth.model.bo.SysUserForm;
import com.zdf.auth.model.dto.SysUserDTO;
import com.zdf.auth.model.po.SysUserToWarehouse;
import com.zdf.auth.model.vo.SysUserVO;
import com.zdf.auth.service.ISysUserToWarehouseService;
import com.zdf.common.RedisUtils;
import com.zdf.common.constant.CommonConstant;
import com.zdf.common.utils.Captcha;
import com.zdf.common.utils.CreateImageCode;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.common.utils.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.common.constant.SysTips;
import com.zdf.common.BaseResult;
import com.zdf.auth.service.ISysUserService;
import com.zdf.auth.model.po.SysUser;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台用户表 控制类
 *
 * @author jayud
 * @since 2022-02-21
 */
@Slf4j
@Api(tags = "后台用户表")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {


    @Autowired
    public ISysUserService sysUserService;
    @Autowired
    private ISysUserToWarehouseService sysUserToWarehouseService;

    @Autowired
    RedisUtils redisUtils;

    /**
     * @description 分页查询
     * @author jayud
     * @date 2022-02-21
     * @param: sysUser
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.zdf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage < com.zdf.auth.model.po.SysUser>>
     **/
    @ApiOperation("分页查询数据")
    @PostMapping("/selectPage")
    public BaseResult<IPage<SysUserVO>> selectPage(@RequestBody SysUserForm sysUserForm, HttpServletRequest req) {

        if (CurrentUserUtil.hasRole(CommonConstant.SUPER_TENANT)) {
            sysUserForm.setCode(null);
        } else {
            sysUserForm.setCode(CurrentUserUtil.getUserTenantCode());
        }
        return BaseResult.ok(sysUserService.selectPage(sysUserForm, sysUserForm.getCurrentPage(), sysUserForm.getPageSize(), req));
    }


    /**
     * @description 列表查询数据
     * @author jayud
     * @date 2022-02-21
     * @param: sysUser
     * @param: req
     * @return: com.zdf.common.BaseResult<java.util.List < com.zdf.auth.model.po.SysUser>>
     **/
    @ApiOperation("列表查询数据")
    @PostMapping("/selectList")
    public BaseResult<List<SysUserVO>> selectList(@RequestBody SysUser sysUser,
                                                  HttpServletRequest req) {
        return BaseResult.ok(sysUserService.selectList(sysUser));
    }

    @ApiOperation("列表查询数据")
    @PostMapping("/api/selectListFeign")
    public BaseResult selectListFeign() {
        SysUser sysUser = new SysUser();
        if (CurrentUserUtil.hasRole(CommonConstant.SUPER_TENANT)) {
            sysUser.setCode(null);
        } else {
            sysUser.setCode(CurrentUserUtil.getUserTenantCode());
        }
        List<SysUserVO> sysUserVOS = sysUserService.selectList(sysUser);
        System.out.println("远程调用查询到的数据：" + sysUserVOS);
        return BaseResult.ok(sysUserVOS);
    }

    /**
     * @description 根据ids查询数据  没用到
     **/
    @ApiOperation("根据ids查询数据")
    @PostMapping("/selectIdsList")
    public BaseResult<List<SysUserVO>> selectIdsList(@RequestBody DeleteForm ids) {
        //需要写一个根据id集合查询列表信息
        SysUserForm sysUserForm = new SysUserForm();
        sysUserForm.setRoleIds(ids.getIds());
        List<SysUserVO> sysUserVOS = sysUserService.selectIdsList(sysUserForm);
        return BaseResult.ok(sysUserVOS);
    }


    /**
     * @description 新增
     * @author jayud
     * @date 2022-02-21
     * @param: sysUser
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("新增")
    @PostMapping("/saveOrUpdate")
    public BaseResult add(@RequestBody SysUserForm sysUserForm) {
        if (sysUserForm == null) {
            return BaseResult.error("数据不能为空！");
        }
        if (sysUserForm.getId() == null) {
            SysUser sysUserNameOne = sysUserService.findSysUserNameOne(sysUserForm);
            if (sysUserNameOne != null) {
                return BaseResult.error("用户名已存在！");
            }
        }
        if (sysUserForm.getId() != null) {
            //校验非当前的用户名称
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysUser::getName, sysUserForm.getName());
            lambdaQueryWrapper.eq(SysUser::getIsDeleted, false);
            lambdaQueryWrapper.ne(SysUser::getId, sysUserForm.getId());
            SysUser one = sysUserService.getOne(lambdaQueryWrapper);
            if (one != null) {
                return BaseResult.error("用户名已存在！");
            }
        }
        boolean b = sysUserService.saveOrUpdateSysUser(sysUserForm);
        if (b) {
            return BaseResult.ok();
        }
        return BaseResult.error(SysTips.ADD_SUCCESS);
    }


//    /**
//     * @description 编辑
//     * @author jayud
//     * @date 2022-02-21
//     * @param: sysUser
//     * @return: com.zdf.common.BaseResult
//     **/
//    @ApiOperation("编辑")
//    @PostMapping("/edit")
//    public BaseResult edit(@Valid @RequestBody SysUser sysUser) {
//        sysUserService.updateById(sysUser);
//        return BaseResult.ok(SysTips.EDIT_SUCCESS);
//    }


//    /**
//     * @description 物理删除
//     * @author jayud
//     * @date 2022-02-21
//     * @param: id
//     * @return: com.zdf.common.BaseResult
//     **/
//    @ApiOperation("物理删除")
//    @ApiImplicitParam(name = "id", value = "主键id", dataType = "Long", required = true)
//    @GetMapping("/phyDel")
//    public BaseResult phyDel(@RequestParam Long id) {
//        sysUserService.phyDelById(id);
//        return BaseResult.ok(SysTips.DEL_SUCCESS);
//    }

    /**
     * @description 逻辑删除
     * @author jayud
     * @date 2022-02-21
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @PostMapping("/delSysUser")
    public BaseResult logicDel(@RequestBody DeleteForm ids) {
        if (ids.getIds().size() == 0) {
            return BaseResult.error("id不为空");
        }

        return sysUserService.deleteSysUser(ids.getIds());
    }


    /**
     * @description 根据id查询
     * @author jayud
     * @date 2022-02-21
     * @param: id
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.SysUser>
     **/
    @ApiOperation("根据id查询")
    @GetMapping(value = "/queryById")
    public BaseResult<SysUserVO> queryById(@RequestParam(name = "id", required = true) Long id) {

        SysUserForm sysUserForm = new SysUserForm();
        sysUserForm.setId(id);
        //关联查询用户信息 关联表 行合并  成列
        BaseResult sysUserIdOne1 = sysUserService.findSysUserIdOne(sysUserForm);
        return sysUserIdOne1;
    }


    @ApiOperation("修改密码")
    @PostMapping("/updateUserPassword")
    public BaseResult updateUserPassword(@RequestBody SysUserForm sysUserForm) {
        sysUserService.findUpdateUserPassword(sysUserForm);
        return BaseResult.ok();
    }

    @ApiOperation("根据角色编码查询用户")
    @ApiImplicitParam(name = "roleCode", value = "角色编码", dataType = "String", required = true)
    @PostMapping("/selectUserByRoleCode")
    public BaseResult<List<SysUserDTO>> selectUserByRoleCode(@RequestParam String roleCode,
                                                             HttpServletRequest req) {
        return BaseResult.ok(sysUserService.selectUserByRoleCode(roleCode));
    }

    @ApiOperation("根据登录名称查询用户")
    @ApiImplicitParam(name = "username", value = "登录名称", dataType = "String", required = true)
    @GetMapping("/selectByUsername")
    public BaseResult<SysUser> selectByUsername(@RequestParam("userName") String userName,
                                                   HttpServletRequest req) {
        return BaseResult.ok(sysUserService.selectByUsername(userName));
    }

    @ApiOperation("根据用户id称查询用户")
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", required = true)
    @PostMapping("/selectByUserId")
    public BaseResult<SysUser> selectByUserId(@RequestParam Long userId,
                                              HttpServletRequest req) {
        return BaseResult.ok(sysUserService.selectByUserId(userId));
    }

    @ApiOperation("根据仓库查询用户")
    @GetMapping("/getUserByWarehouse")
    public BaseResult<List<SysUser>> getUserByWarehouse(SysUserToWarehouse sysUserToWarehouse) {
        return BaseResult.ok(sysUserService.getUserByWarehouse(sysUserToWarehouse));
    }


    /**
     * 调取获得验证码  获取验证码不重复 传时间戳
     * 生成验证码
     * http://localhost:8001/jayudAuth/sysUser/getImage?date=12312
     *
     * @throws IOException
     */
    @ApiOperation("调取获得验证码")
    @GetMapping("/getImage")
    public BaseResult getImage(HttpServletRequest request) throws IOException {
        //设置Redis路径
        String s = "verification:verificationCode";
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        //获取验证码
        String securityCode = createImageCode.getCode();

        long l = System.currentTimeMillis();
        String keyVerification = s + l;
        //保存到redis
        redisUtils.set(keyVerification, securityCode.toUpperCase(), 60);
        //生成图片
        BufferedImage image = createImageCode.getBuffImg();

        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("keyVerification", keyVerification);
        result.put("image", string);
        return BaseResult.ok(result);
    }

}
