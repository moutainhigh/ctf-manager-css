package com.ctf.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysDept;
import com.ctf.admin.pojo.query.DeptQuery;
import com.ctf.admin.pojo.vo.dept.DeptVO;
import com.ctf.common.web.domain.Option;

import java.util.List;

/**
 * 菜单路由业务接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * 部门列表
     *
     * @return
     */
    List<DeptVO> listDepts(DeptQuery queryParams);

    /**
     * 部门树形下拉（TreeSelect）层级列表
     *
     * @return
     */
    List<Option> lisetDeptOptions();

    /**
     * 新增/修改部门
     *
     * @param dept
     * @return
     */
    Long saveDept(SysDept dept);

    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return
     */
    boolean deleteByIds(String ids);
}
