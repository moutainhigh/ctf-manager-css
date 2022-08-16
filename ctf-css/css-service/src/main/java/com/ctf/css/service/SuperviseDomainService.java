package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.web.domain.Option;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.form.SuperviseDomainForm;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;

import java.util.List;

/**
* @author zhangyizheng
* @description 针对表【store_supervise_domain(督导领域表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface SuperviseDomainService extends IService<SuperviseDomain> {

    /**
     * 督导领域分页查询
     * @param queryParams 分页条件
     * @return
     */
    Page<SuperviseDomainVo> pageSupervise(SupervisorDomainPageQuery queryParams);

    /**
     * 新增督导领域
     * @param superviseDomainForm 督导领域表单
     * @return
     */
    boolean saveSuperviseDomain(SuperviseDomainForm superviseDomainForm);

    /**
     * 修改督导领域
     * @param  superviseDomainId,superviseDomainForm 督导领域表单
     * @return
     */
    boolean updateSuperviseDomain(Long superviseDomainId, SuperviseDomainForm superviseDomainForm);

    /**
     * 删除督导领域
     * @param superviseDomainIds
     * @return
     */
    boolean deleteSuperviseDomain(String superviseDomainIds);

    /**
     * 督导领域下拉列表
     *
     * @return
     */
    List<Option> listRoleOptions();
}
