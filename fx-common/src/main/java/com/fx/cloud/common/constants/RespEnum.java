package com.fx.cloud.common.constants;

/**
 * @author Administrator
 * 保存 响应码
 */

public enum RespEnum {

    INVALID_TOKEN(200, "success"),
    /**
     * oauth2返回码
     */

    EMPTY_TOKEN(201, "empty_token"),

    ACCESS_DENIED(403, "request_forbidden"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "bad_request"),
    NOT_FOUND(404, "not_found"),

    /**
     * 系统错误
     */
    ERROR(500, "error"),
    GATEWAY_TIMEOUT(504, "gateway_timeout"),
    SERVICE_UNAVAILABLE(503, "service_unavailable"),
    RES(null, "");


    private Integer code;

    private String message;

    private RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
