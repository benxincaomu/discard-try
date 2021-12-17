package com.github.benxincaomu.notry.exception.handler;

import com.github.benxincaomu.notry.code.ResponseCode;
/**
 * 定义响应格式
 * @author sunfutao
 */
public class ResponseMessage<T> {
    private int code;
    private String message;
    private Object data;
    private final long time = System.currentTimeMillis();

    public ResponseMessage(int code, String message, T data, Object... params) {
        this.code = code;
        if (params == null || params.length == 0) {
            this.message = message;
        } else {
            this.message = String.format(message, params);
        }
        this.data = data;
    }

    public ResponseMessage(ResponseCode code, T data, Object... params) {
        this.code = code.getCode();
        if (params == null || params.length == 0) {
            this.message = code.getMessage();
        } else {
            this.message = String.format(code.getMessage(), params);
        }
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public long getTime() {
        return time;
    }
}
