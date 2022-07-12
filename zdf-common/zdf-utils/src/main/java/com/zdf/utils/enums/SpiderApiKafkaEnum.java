package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpiderApiKafkaEnum {
    LENOVO_SPIDER_EXTERNAL_API("lenovo-spider-external-api"),
    ;
    private String name;


    @Getter
    @AllArgsConstructor
    public enum Status {
        // 1未消费，2已消费，3异常
        NOT_CONSUMED(1),CONSUMED(2),EXCEPTION(3),
        ;
        Integer status;
    }
}
