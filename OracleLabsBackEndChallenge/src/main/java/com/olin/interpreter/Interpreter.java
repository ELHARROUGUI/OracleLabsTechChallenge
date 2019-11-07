package com.olin.interpreter;

public abstract class Interpreter {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String execute(String code);
}
