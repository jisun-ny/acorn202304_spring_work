package com.gura.spring04.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * 컨트롤러에서 정보를 처리하다가 특정조건에서 예외를 발생시켜야한는 시점이라면 throw new NotDeleteException(); 하면
 * 실행의 흐름이 @ControllerAdvice로 뛴다.
 * Spring MVC가 동작 중에 특정 type의 예외가 발생하면 해당 예외를 여기서 처리할 수 있다.
 */

@ControllerAdvice
public class ExceptionController {
	//spring framework 가 동작하는 중에 NotDeleteException type 의 
	//예외가 발생하면 호출되는 메소드 
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) { //메소드의 인자로 예외 객체가 전달된다.

		ModelAndView mView=new ModelAndView();
		//exception 이라는 키값으로 예외 객체를 담고 
		mView.addObject("exception", nde);
		//view page  ( /WEB-INF/views/error/info.jsp ) 로 forward 이동해서 예외 정보 응답하기  
		mView.setViewName("error/info");
		return mView;
	}	
}
