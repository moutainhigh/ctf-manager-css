package com.zdf.utils.result;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @anthor Satellite
 * Result
 * 消息返回实体类
 * http://www.javalow.com
 * @date 2018-11-18-15:37
 **/
@AllArgsConstructor
@Data
public class Result<T> {

    /** 错误码. */
    @ApiModelProperty(value = "返回码", dataType = "Integer")
    private String code;

    /** 提示信息. */
    @ApiModelProperty(value = "提示信息", dataType = "String")
    private String msg;

    /** 具体内容. */
    @ApiModelProperty(value = "具体内容")
    private T data;

    /**
     * 成功结果
     * @param data 成功结果
     * @return
     */
    public static <T> Result<T> success(T data) {
        ObjectMapper mapper = new ObjectMapper();
        return new Result<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功返回结果
     * @param <T>
     * @return
     */
    public static <T>  Result<T> success() {
        return success(null);
    }



    /**
     * 失败返回结果
     * @param code  错误码
     * @param msg   提示信息
     * @param <T>
     * @return
     */
    public static <T> Result error(String code, String msg) {
        return error(code,msg,null);
    }


    /**
     * 失败返回结果
     * @param code  错误码
     * @param msg   提示信息
     * @param data  具体内容
     * @param <T>
     * @return
     */
    public static <T> Result error(String code, String msg,T data) {
        return new Result<T>(code,msg,data);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @return
     */
    public static Result error(ResultCode errorCode) {
        return error(errorCode.getCode(),errorCode.getMessage());
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(IResultCode errorCode, T data) {
        return error(errorCode.getCode(),errorCode.getMessage(),data);
    }

}
