package com.zdf.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.mapper.SeaPortMapper;
import com.zdf.auth.model.bo.AddSeaPortForm;
import com.zdf.auth.model.bo.QuerySeaPortForm;
import com.zdf.auth.model.po.SeaPort;
import com.zdf.auth.model.vo.SeaPortVO;
import com.zdf.auth.service.ISeaPortService;
import com.zdf.common.UserOperator;
import com.zdf.common.entity.InitComboxStrVO;
import com.zdf.common.utils.ConvertUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 船港口地址表 服务实现类
 * </p>
 *
 * @author LLJ
 * @since 2021-01-29
 */
@Service
public class SeaPortServiceImpl extends ServiceImpl<SeaPortMapper, SeaPort> implements ISeaPortService {

    @Override
    public List<InitComboxStrVO> initSeaPort() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        List<SeaPort> list = baseMapper.selectList(queryWrapper);
        List<InitComboxStrVO> initComboxStrVOS = new ArrayList<>();
        for (SeaPort seaPort : list) {
            InitComboxStrVO initComboxStrVO = new InitComboxStrVO();
            initComboxStrVO.setCode(seaPort.getCode());
            initComboxStrVO.setName(seaPort.getName());
            initComboxStrVOS.add(initComboxStrVO);
        }
        return initComboxStrVOS;
    }

    @Override
    public String getPortName(String portDepartureCode) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code",portDepartureCode);
        SeaPort one = this.getOne(queryWrapper);
        if(one != null){
            return one.getName();
        }
        return portDepartureCode;
    }

    @Override
    public IPage<SeaPortVO> findByPage(QuerySeaPortForm form) {
        Page<SeaPortVO> page = new Page<>(form.getCurrentPage(), form.getPageSize());
        return this.baseMapper.findByPage(page, form);
    }

    @Override
    public SeaPort isCodeExistence(String code) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code",code);
        return this.getOne(queryWrapper);
    }

    @Override
    public SeaPort isNameExistence(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean saveOrUpdateSeaPort(AddSeaPortForm form) {
        SeaPort convert = ConvertUtil.convert(form, SeaPort.class);
        convert.setCreateTime(new Date());
        convert.setCreateBy(UserOperator.getToken());
        convert.setStatus(1);
        return this.saveOrUpdate(convert);
    }

    @Override
    public boolean deleteSeaPort(Long id) {
        SeaPort seaPort = this.getById(id);
        seaPort.setStatus(0);
        return this.saveOrUpdate(seaPort);
    }

    @Override
    public boolean saveOrUpdateBatchSeaPort(List<AddSeaPortForm> list) {

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getCode() == null || list.get(i).getCode() ==""){
                list.remove(list.get(i));
            }
            if(this.isCodeExistence(list.get(i).getCode()) != null){
                list.remove(list.get(i));
            }
            if(this.isNameExistence(list.get(i).getName()) != null){
                list.remove(list.get(i));
            }
            for (int i1 = i+1; i1 < list.size(); i1++) {
                if(list.get(i).getCode().equals(list.get(i1).getCode())){
                    list.remove(list.get(i1));
                }
                if(list.get(i).getName().equals(list.get(i1).getName())){
                    list.remove(list.get(i1));
                }
            }
        }

        List<SeaPort> seaPorts = ConvertUtil.convertList(list, SeaPort.class);
        for (SeaPort seaPort : seaPorts) {
            seaPort.setStatus(1);
            seaPort.setCreateBy("admin");
            seaPort.setCreateTime(new Date());
        }
        return this.saveOrUpdateBatch(seaPorts);
    }
}
