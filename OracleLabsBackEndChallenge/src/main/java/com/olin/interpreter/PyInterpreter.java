package com.olin.interpreter;

import java.io.ByteArrayOutputStream;

import org.python.util.PythonInterpreter;

public class PyInterpreter extends Interpreter {

	private PythonInterpreter pyInterpreter;

	public PyInterpreter() {
		super();
		this.pyInterpreter = new PythonInterpreter();
	}

	public PythonInterpreter getPyInterpreter() {
		return pyInterpreter;
	}

	public void setPyInterpreter(PythonInterpreter pyInterpreter) {
		this.pyInterpreter = pyInterpreter;
	}

	@Override
	public String execute(String code) {
		ByteArrayOutputStream pyOut = new ByteArrayOutputStream();
		this.pyInterpreter.setOut(pyOut);
		this.pyInterpreter.exec(code);
		return pyOut.toString();
	}
}
