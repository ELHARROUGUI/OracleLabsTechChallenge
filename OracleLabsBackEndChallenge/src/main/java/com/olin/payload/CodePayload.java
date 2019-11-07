package com.olin.payload;

public class CodePayload extends Payload {
	private String code;

	public CodePayload() {
		super();
		this.code = "";
	}

	public CodePayload(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
