package com.thinven.boot.controller.exceptionset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("key", "ERR_EXCEPTION");
		mav.addObject("desc", "파일 용량을 줄이셔야 합니다.");
		return mav;
	}
}
