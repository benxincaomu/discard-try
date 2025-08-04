package io.github.benxincaomu.notry.exception;

import io.github.benxincaomu.notry.code.ResponseCode;

/**
 * 通用业务异常，主动抛出表示业务流程未正常返回
 * 
 * @author sunft
 *
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 3429951046411683825L;

	private int code;

	private String message;

	/**
	 * 业务处理错误时额外返回的数据
	 */
	private Object data;

	public CommonException(ResponseCode code, Object... params) {
		super();
		this.code = code.getCode();
		if (params.length > 0) {
			this.message = String.format(code.getMessage(), params);
		} else {
			this.message = code.getMessage();
		}
	}

	public CommonException(ResponseCode code, Object data, Object... params) {
		super();
		this.code = code.getCode();
		this.data = data;
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

	public Object getData() {
		return data;
	}

}
