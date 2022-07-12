package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.po.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典 服务类
 *
 * @author jayud
 * @since 2022-02-23
 */
public interface ISysDictService extends IService<SysDict> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-23
     * @param: sysDict
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysDict>
     **/
    IPage<SysDict> selectPage(SysDict sysDict,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-23
     * @param: sysDict
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.SysDict>
     **/
    List<SysDict> selectList(SysDict sysDict);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-23
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-02-23
    * @param: id
    * @return: com.jyd.component.commons.result.Result
    **/
    void logicDel(Long id);


    void checkUnique(SysDict sysDict);
}
