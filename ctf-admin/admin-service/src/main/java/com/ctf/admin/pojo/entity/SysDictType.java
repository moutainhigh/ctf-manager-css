package com.ctf.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ctf.common.base.BaseEntity;
import lombok.Data;

@Data
public class SysDictType extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Integer status;

    private  String  remark;
    private Long tenantId;

}
