package com.auth.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Value("${base_url}")
	private String baseUrl;

	@ExceptionHandler(NoResourceFoundException.class)
	public String handleNotFound(NoResourceFoundException ex) {
		System.err.println("handleNotFound");
		System.err.println(baseUrl);
		return "redirect:" + baseUrl + "/login";
	}
}
