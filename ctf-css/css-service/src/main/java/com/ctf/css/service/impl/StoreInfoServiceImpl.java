package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.StoreInfo;
import com.ctf.css.service.StoreInfoService;
import com.ctf.css.mapper.StoreInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_info(门店信息表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
public class StoreInfoServiceImpl extends ServiceImpl<StoreInfoMapper, StoreInfo>
    implements StoreInfoService{

}




