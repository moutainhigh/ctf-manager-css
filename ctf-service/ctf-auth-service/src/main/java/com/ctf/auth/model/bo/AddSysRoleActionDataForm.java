package com.ctf.auth.model.bo;


import com.ctf.auth.model.po.SysRoleActionData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 角色数据权限
 * </p>
 *
 * @author LLJ
 * @since 2021-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddSysRoleActionDataForm {

    @ApiModelProperty(value = "新增角色数据权限")
    private List<SysRoleActionData> sysRoleActionDatas;

}
