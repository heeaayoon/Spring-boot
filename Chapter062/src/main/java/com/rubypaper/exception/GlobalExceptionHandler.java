package com.rubypaper.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public String handleCustomException(BoardException e, Model model) {
		model.addAttribute("exception", e);
		return "errors/boardError"; //templates 안의 boardError.html 호출
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("exceptionMessage", e.getMessage());
		model.addAttribute("stackTrace", e.getStackTrace());
		return "/errors/globalError";
	}
}
