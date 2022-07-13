package com.ctf.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.bo.QueryCommonConfigForm;
import com.ctf.auth.model.bo.QuerySystemSqlConfigForm;
import com.ctf.auth.model.bo.SystemSqlConfigForm;
import com.ctf.auth.model.po.SystemSqlConfig;
import com.ctf.auth.model.vo.SystemSqlConfigVO;
import com.ctf.auth.model.vo.TableColumnVO;
import com.ctf.common.CommonPageResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * SQL数据源配置 服务类
 * </p>
 *
 * @author LLJ
 * @since 2021-08-09
 */
public interface ISystemSqlConfigService extends IService<SystemSqlConfig> {

    /**
     * 分页查询
     * @param form
     * @return
     */
    IPage<SystemSqlConfigVO> findByPage(QuerySystemSqlConfigForm form);

    /**
     * 根据id，查询
     * @param id
     * @return
     */
    SystemSqlConfigVO getSystemSqlConfigById(Integer id);

    /**
     * 保存
     * @param form
     */
    void saveSystemSqlConfig(SystemSqlConfigForm form);

    /**
     * 根据id删除
     * @param id
     */
    void delSystemSqlConfig(Integer id);

    /**
     * 根据SQL代码，获取表格列
     * @param sqlCode
     * @return
     */
    List<TableColumnVO> getTableColumn(String sqlCode);

    /**
     * 根据SQL代码和通用条件分页查询,返回泛型map
     * @param form
     * @return
     */
    CommonPageResult<Map<String, Object>> findCommonByPage(QueryCommonConfigForm form);
}
