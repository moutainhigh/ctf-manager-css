package com.ctf.utils.result;

import com.ctf.utils.vo.IResultCode;
import com.ctf.utils.vo.ResultEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共结果返回
 *
 * @param <T>
 * @author bocong.zheng
 */
@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 3068837394742385883L;

    /**
     * 错误码.
     */
    @ApiModelProperty(value = "返回码", dataType = "Integer", position = 1)
    private Integer code;

    /**
     * 提示信息.
     */
    @ApiModelProperty(value = "提示信息", dataType = "String", position = 2)
    private String msg;

    /**
     * 具体内容.
     */
    @ApiModelProperty(value = "具体内容", position = 3)
    private T data;


    public CommonResult() {
    }

    /**
     * 成功结果
     *
     * @param data 成功结果
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success() {
        return success(null);
    }


    /**
     * 失败返回结果
     *
     * @param code 错误码
     * @param msg  提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult error(Integer code, String msg) {
        return error(code, msg, null);
    }


    /**
     * 失败返回结果
     *
     * @param code 错误码
     * @param msg  提示信息
     * @param data 具体内容
     * @param <T>
     * @return
     */
    public static <T> CommonResult error(Integer code, String msg, T data) {
        return new CommonResult<T>(code, msg, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode
     * @return
     */
    public static CommonResult error(IResultCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 失败返回结果
     *
     * @param errorCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(IResultCode errorCode, T data) {
        return error(errorCode.getCode(), errorCode.getMessage(), data);
    }

}