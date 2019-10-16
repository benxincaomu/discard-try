package io.github.benxincaomu.notry.exception;

import io.github.benxincaomu.notry.code.ResponseCode;

public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 3429951046411683825L;

    private int code;
    private String message;

    public CommonException(ResponseCode code, Object... params) {
        super();
        this.code = code.getCode();
        if (params.length > 0) {
            this.message = String.format(code.getMessage(), params);
        } else {
            this.message = code.getMessage();
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
