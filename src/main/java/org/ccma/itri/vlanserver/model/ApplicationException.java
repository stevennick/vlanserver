package org.ccma.itri.vlanserver.model;

public class ApplicationException {

	private int code;
	private String reason;
	private String message;

	public ApplicationException() {

	}

	public ApplicationException(int code, String reason, String message) {
		this.code = code;
		this.reason = reason;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
