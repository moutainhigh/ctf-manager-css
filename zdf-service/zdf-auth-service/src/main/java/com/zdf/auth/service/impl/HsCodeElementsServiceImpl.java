package com.zdf.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zdf.auth.mapper.HsCodeElementsMapper;
import com.zdf.auth.model.po.HsCodeElements;
import com.zdf.auth.model.vo.HsCodeElementsVO;
import com.zdf.auth.service.IHsCodeElementsService;
import com.zdf.common.utils.ConvertUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 海关税号要素明细表 服务实现类
 * </p>
 *
 * @author LLJ
 * @since 2021-07-26
 */
@Service
public class HsCodeElementsServiceImpl extends ServiceImpl<HsCodeElementsMapper, HsCodeElements> implements IHsCodeElementsService {

    @Override
    public boolean deleteCodeElementsByCodeNo(String codeNo) {
        QueryWrapper<HsCodeElements> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(HsCodeElements::getHsCodeNo,codeNo);
        return this.remove(queryWrapper);
    }

    /**
     * 根据海关编号获取申报要素
     * @param codeNo
     * @return
     */
    @Override
    public List<HsCodeElementsVO> getCodeElementsByCodeNo(String codeNo) {
        QueryWrapper<HsCodeElements> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HsCodeElements::getHsCodeNo , codeNo);
        List<HsCodeElements> list = this.list(queryWrapper);
        List<HsCodeElementsVO> hsCodeElementsVOS = ConvertUtil.convertList(list, HsCodeElementsVO.class);
        return hsCodeElementsVOS;
    }
}
