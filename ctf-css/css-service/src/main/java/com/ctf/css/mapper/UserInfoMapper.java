package com.ctf.css.mapper;

import com.ctf.css.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhangyizheng
* @description 针对表【user_info(用户信息)】的数据库操作Mapper
* @createDate 2022-08-11 09:25:19
* @Entity com.ctf.css.pojo.entity.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




