package com.ctf.css.pojo.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author trok
 * @Date 2022/8/12 17:15
 * @Describe SuperviseDomainForm
 */
@ApiModel(value = "督导领域名称")
@Data
public class SuperviseDomainForm {
    /**
     * 督导领域名称
     */
    private String superviseDomainName;
    /**
     * 督导领域状态
     */
    private String status;
}