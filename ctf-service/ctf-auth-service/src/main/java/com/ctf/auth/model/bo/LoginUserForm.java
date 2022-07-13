package com.ctf.auth.model.bo;

import com.ctf.auth.model.po.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author ciro
 * @date 2022/2/24 15:54
 * @description: 登录用户信息
 */
@Data
public class LoginUserForm extends SysUser {

    private List<String> roleCodeList;

}
