package com.ctf.css.pojo.vo.ex;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 17:45
 * @Describe SuperviseDomainVo
 */
@Data
public class SuperviseDomainVo {
    /**
     * 雪花ID
     */
    @TableId
    private Long id;

    /**
     * 领域名称
     */
    private String superviseDomainName;

    /**
     * 状态(1:启用 0:禁用)
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
