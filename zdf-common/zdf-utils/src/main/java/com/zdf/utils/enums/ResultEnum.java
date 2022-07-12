package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),

    SUCCESS(0, "Got all search result and display successfully, Message:success"),

    PARAM_ERROR(1,"The parameter is incorrect"),

    UPDATE_ERROR(2,"更新数据失败"),

    SAVE_ERROR(3,"更新数据失败"),

    LOGIN_FAIL(25, "Logon failed. Logon information is incorrect"),

    VALIDATE_FAILED(404, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),

    UNSUPPORTED_MEDIA_TYPE(415, "不支持当前媒体类型"),

    UNPROCESSABLE_ENTITY(422, "所上传文件大小超过最大限制，上传失败"),

    INTERNAL_SERVER_ERROR(500, "Server internal error"),

    APPID_SECRET_ERROR(202,"Appid and secret error"),

    //================consumption异常========================================
    FIELD_DATA_EMPTY(420,"Field data is empty");


    ;
    private Integer code;
    private String message;

}
