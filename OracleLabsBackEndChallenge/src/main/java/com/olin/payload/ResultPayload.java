package com.olin.payload;

public class ResultPayload extends Payload {
	private String result;

	public ResultPayload() {
		this.result = "";
	}

	public ResultPayload(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
