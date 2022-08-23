package com.ctf.css.converter;

import com.ctf.common.web.domain.Option;
import com.ctf.css.pojo.entity.TourSupervisor;
import com.ctf.css.pojo.entity.UserInfo;
import com.ctf.css.pojo.form.SuperviseForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

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
