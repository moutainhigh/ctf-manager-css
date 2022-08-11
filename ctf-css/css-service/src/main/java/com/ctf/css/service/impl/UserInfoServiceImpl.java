package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.UserInfo;
import com.ctf.css.service.UserInfoService;
import com.ctf.css.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【user_info(用户信息)】的数据库操作Service实现
* @createDate 2022-08-11 09:25:19
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




