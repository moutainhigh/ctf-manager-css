package com.ctf.common.web.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Canal 消息对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@NoArgsConstructor
@Data
public class CanalMessage<T> {
    private String type;
    private String table;
    private List<T> data;
    private String database;
    private Long es;
    private Integer id;
    private Boolean isDdl;
    private List<T> old;
    private List<String> pkNames;
    private String sql;
    private Long ts;
}
