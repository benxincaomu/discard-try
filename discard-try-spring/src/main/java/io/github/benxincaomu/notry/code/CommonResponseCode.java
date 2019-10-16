package io.github.benxincaomu.notry.code;

public enum CommonResponseCode implements ResponseCode {
    ERROR(500, "系统异常"), SUCCESS(200, "操作成功"),;
    private int code;
    private String message;

    CommonResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
