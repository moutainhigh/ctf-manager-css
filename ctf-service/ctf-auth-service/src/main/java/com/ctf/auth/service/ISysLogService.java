package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.SysLogForm;
import com.ctf.auth.model.po.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.vo.SysLogVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表 服务类
 *
 * @author jayud
 * @since 2022-03-22
 */
public interface ISysLogService extends IService<SysLog> {


    /**
     * @description 分页查询
     * @author jayud
     * @date 2022-03-22
     * @param: sysLog
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysLog>
     **/
    IPage<SysLogVO> selectPage(SysLogForm sysLogForm,
                               Integer currentPage,
                               Integer pageSize,
                               HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author jayud
     * @date 2022-03-22
     * @param: sysLog
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysLog>
     **/
    List<SysLog> selectList(SysLog sysLog);


    void saveOrUpdateSysLog(SysLog sysLog);


   void saveOrUpdateSysLogClient(String logContent,Long businessId);
    /**
     * @description 物理删除
     * @author jayud
     * @date 2022-03-22
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
     * @description 逻辑删除
     * @author jayud
     * @date 2022-03-22
     * @param: id
     * @return: com.ctf.component.commons.result.Result
     **/
    void logicDel(Long id);


    /**
     * @description 查询导出
     * @author jayud
     * @date 2022-03-22
     * @param: queryReceiptForm
     * @param: req
     * @return: java.util.List<java.util.LinkedHashMap < java.lang.String, java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> querySysLogForExcel(SysLogForm sysLogForm);


}
