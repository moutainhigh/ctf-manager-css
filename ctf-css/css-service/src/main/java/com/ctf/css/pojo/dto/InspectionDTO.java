package com.ctf.css.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/20 09:58
 * @Describe InspectionDTO
 */
@Data
public class InspectionDTO {
    /**
     * 雪花ID
     */
    private Long id;

    /**
     * 巡检类型 0独立巡检 1联合巡检 2门店自检
     */
    private Integer inspectionType;
}
