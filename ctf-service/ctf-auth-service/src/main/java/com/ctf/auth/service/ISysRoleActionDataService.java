package com.ctf.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.AddSysRoleActionDataForm;
import com.ctf.auth.model.bo.QueryForm;
import com.ctf.auth.model.po.SysRoleActionData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.vo.SysRoleActionDataVO;
import com.ctf.common.BaseResult;

import java.util.List;

/**
 * <p>
 * 角色数据权限 服务类
 * </p>
 *
 * @author LLJ
 * @since 2021-08-02
 */
public interface ISysRoleActionDataService extends IService<SysRoleActionData> {

    /**
     * 分页查询数据
     * @param form 查询参数实体
     * @return
     */
    IPage<SysRoleActionDataVO> findByPage(QueryForm form);

    /**
     * 新增数据权限
     * @param form 新增实体类
     * @return
     */
    boolean addSystemRoleActionData(AddSysRoleActionDataForm form);

    /**
     * 获取拥有该菜单权限的角色有哪些
     * @param actionCode 菜单code
     * @return
     */
    List<Integer> getRoleData(String actionCode);

    /**
     * 根据角色id获取该角色的数据权限
     * @param sysRoleActionData 查询参数实体类
     * @return
     */
    BaseResult<List<SysRoleActionData>> selectList(SysRoleActionData sysRoleActionData);
}
