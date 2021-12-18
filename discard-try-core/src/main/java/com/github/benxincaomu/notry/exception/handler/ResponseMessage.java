package com.github.benxincaomu.notry.exception.handler;

import com.github.benxincaomu.notry.code.CommonResponseCode;
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
        super();
        this.code = code;
        if (params == null || params.length == 0) {
            this.message = message;
        } else {
            this.message = String.format(message, params);
        }
        this.data = data;
    }

    public ResponseMessage(ResponseCode code, T data, Object... params) {
        this(code.getCode(),code.getMessage(),data,params);
    }

    public ResponseMessage(){
        super();
        this.code = CommonResponseCode.SUCCESS.getCode();
        this.message = CommonResponseCode.SUCCESS.getMessage();
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
