package io.github.benxincaomu.notry.exception.handler;

import io.github.benxincaomu.notry.code.ResponseCode;

public class ResponseMessage {
    private int code;
    private String message;
    private Object data;

    public ResponseMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(ResponseCode code, Object data, Object... params) {
        this.code = code.getCode();
        if (params == null || params.length == 0) {
            this.message = code.getMessage();
        } else {
            this.message = String.format(code.getMessage(), params);
        }
        this.data = data;
    }

}
