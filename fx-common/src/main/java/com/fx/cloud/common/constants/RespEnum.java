package com.fx.cloud.common.constants;

/**
 * @author Administrator
 * 保存 响应码
 */

public enum RespEnum {

    RES("", "");

    private String code;

    private String message;

    private RespEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
