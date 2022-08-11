package com.ctf.css.converter;

import com.ctf.css.pojo.entity.UserInfo;
import com.ctf.css.pojo.form.SuperviseForm;
import org.mapstruct.Mapper;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 14:15
 * @Describe SupervisorConverter 督导人员对象转换器
 */
@Mapper(componentModel = "spring")
public interface SupervisorConverter {

    SuperviseForm userInfoToForm(UserInfo userInfo);

    UserInfo formToUserInfo(SuperviseForm superviseForm);
}
