package com.olin.context;

import com.olin.interpreter.Interpreter;
import com.olin.interpreter.PyInterpreter;
import com.olin.payload.CodePayload;
import com.olin.payload.Payload;
import com.olin.payload.PayloadParser;
import com.olin.payload.ResultPayload;

public class Context {

	private String sessionId;
	private String interpreterName;
	private Interpreter interpreter;

	public Context() {
		super();
	}

	public Context(String sessionId, CodePayload payloadIn) {
		super();
		PayloadParser parser = new PayloadParser(payloadIn);
		this.sessionId = sessionId;
		this.interpreterName = parser.getInterpreterName();
		this.interpreter = new PyInterpreter();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Interpreter getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(Interpreter interpreter) {
		this.interpreter = interpreter;
	}

	public String getInterpreterName() {
		return interpreterName;
	}

	public void setInterpreterName(String interpreterName) {
		this.interpreterName = interpreterName;
	}

	public Payload execute(CodePayload payloadIn) {
		PayloadParser parser = new PayloadParser(payloadIn);
		String out = this.interpreter.execute(parser.getCode());
		if (!out.isEmpty())
			return new ResultPayload(out);
		return payloadIn;
	}
}
