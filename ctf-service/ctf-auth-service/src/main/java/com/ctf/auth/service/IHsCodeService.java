package com.ctf.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.bo.AddHsCodeForm;
import com.ctf.auth.model.bo.DeleteForm;
import com.ctf.auth.model.bo.QueryForm;
import com.ctf.auth.model.po.HsCode;
import com.ctf.auth.model.vo.HsCodeFormVO;
import com.ctf.auth.model.vo.HsCodeVO;
import com.ctf.common.CommonResult;

import java.util.List;

/**
 * <p>
 * 海关编码表 服务类
 * </p>
 *
 * @author LLJ
 * @since 2021-07-26
 */
public interface IHsCodeService extends IService<HsCode> {

    /**
     * 分页查询海关编码信息
     * @param form
     * @return
     */
    IPage<HsCodeFormVO> findByPage(QueryForm form);

    /**
     * 删除海关编码信息
     * @param deleteForm
     * @return
     */
    boolean delete(DeleteForm deleteForm);

    /**
     * 保存
     * @param form
     * @return
     */
    boolean saveOrUpdateHsCode(AddHsCodeForm form);

    /**
     * 获取海关编码详情
     * @param id
     * @return
     */
    HsCodeVO getHsCodeDetail(Integer id);

    /**
     * 查找
     * @param codeNo
     * @return
     */
    List<String> findHsCode(String codeNo);

    /**
     * 根据code查找海关编码
     * @param hsCodeNo
     * @return
     */
    HsCodeVO getHsCodeByCodeNo(String hsCodeNo);

    /**
     * 通过code获取海关编码详情
     * @param codeNo
     * @return
     */
    HsCodeVO getHsCodeDetailByCodeNo(String codeNo);

    /**
     * 删除海关编码
     * @param form
     * @return
     */
    CommonResult deleteByIds(DeleteForm form);

    HsCode getByCodeNo(String codeNo);
}
