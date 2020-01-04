package io.github.benxincaomu.notry.code;

/**
 * 自定义响应码
 * <p>
 * message中可使用%s占位符，例如VALIDE_FAILD(6001,"%s字段检验失败")
 * </p>
 * 
 * 
 * @author benxincaomu
 *
 */
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
