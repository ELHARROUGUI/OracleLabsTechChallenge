package com.olin.app;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olin.context.Context;
import com.olin.payload.CodePayload;
import com.olin.payload.Payload;

@RestController
public class ExecuteController {
	Set<Context> contexts = new HashSet<Context>();

	@RequestMapping("/")
	public String index() {
		System.out.println("it works!");
		return "Welcome to OLIN the Oracle Labs Interactive Notebook!";
	}

	@PostMapping("/execute")
	public Payload execute(@RequestBody CodePayload payloadIn, HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		Context context = contexts.stream().filter(x -> x.getSessionId().equals(sessionId)).findAny().orElse(null);
		if (context == null) {
			context = new Context(sessionId, payloadIn);
			contexts.add(context);
		}
		return context.execute(payloadIn);
	}
}
