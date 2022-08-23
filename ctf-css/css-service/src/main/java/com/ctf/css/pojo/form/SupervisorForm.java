package com.ctf.css.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 14:56
 * @Describe SupervisorForm
 */
@ApiModel(value = "巡视门店-督导人员表单信息")
@Data
public class SupervisorForm {

    /**
     * 督导人员id
     */
    private Long id;

    /**
     * 督导领域id
     */
    private Long superviseDomainId;

    /**
     * 巡店时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;
}
