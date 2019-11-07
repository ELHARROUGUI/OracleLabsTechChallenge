package com.olin.payload;

import java.util.stream.IntStream;

public class PayloadParser {

	public String interpreterName;
	public String code;

	public PayloadParser(CodePayload in) {
		String[] inputs = in.getCode().split(" ");
		String[] codeParts = IntStream.range(1, inputs.length).mapToObj(i -> inputs[i]).toArray(String[]::new);
		this.interpreterName = inputs[0];
		this.code = String.join(" ", codeParts);
	}

	public String getInterpreterName() {
		return interpreterName;
	}

	public void setInterpreterName(String interpreterName) {
		this.interpreterName = interpreterName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
