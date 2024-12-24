package com.auth.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	public String handleNotFound(NoResourceFoundException ex) {
		System.err.println("handleNotFound");
		return "redirect:http://localhost:4200/login";
	}
}
