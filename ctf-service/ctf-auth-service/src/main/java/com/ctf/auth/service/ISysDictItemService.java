package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.po.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典项 服务类
 *
 * @author jayud
 * @since 2022-02-23
 */
public interface ISysDictItemService extends IService<SysDictItem> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-23
     * @param: sysDictItem
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysDictItem>
     **/
    IPage<SysDictItem> selectPage(SysDictItem sysDictItem,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-23
     * @param: sysDictItem
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysDictItem>
     **/
    List<SysDictItem> selectList(SysDictItem sysDictItem);



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
    * @return: com.ctf.component.commons.result.Result
    **/
    void logicDel(Long id);

    /**
     * @description 根据字典编码查询子项
     * @author  ciro
     * @date   2022/2/23 9:45
     * @param: dictCode
     * @return: java.util.List<com.ctf.auth.model.po.SysDictItem>
     **/
    List<SysDictItem> selectItemByDictCode(String dictCode);


    void checkUnique(SysDictItem sysDictItem);
}
