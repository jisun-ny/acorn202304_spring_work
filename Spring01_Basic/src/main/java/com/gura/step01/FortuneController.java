package com.gura.step01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FortuneController {
	
	@RequestMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		//request scope에 응답에 필요한 데이터를 담는다.
		request.setAttribute("fortuneToday", fortuneToday);
		
		// WEB-INF/views/fortune.jsp 페이지로 forward 이동해서 응답하기
		return "fortune";
	}
	
	@RequestMapping("/fortune2")
	public ModelAndView fortune2() {
		//모델도 들어있고 뷰페이지 정보도 들어있따... ModelAndView type...zz..
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		
		//HttpServletRequest 객체 대신에 ModelAndView 객체를 생성해서
		ModelAndView mView = new ModelAndView();
		// view page에 전달할 내용을 담는다
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담는다
		mView.setViewName("fortune");
		//모델과 view page 정보가 모두 담겨 있는 ModelAndView 객체를 리턴해주면 똑같이 동작한다.
		return mView;
	}
	
	//ModelAndView를 매개변수로 선언하면 스프링 프레임워크가 알아서 객체 생성 후 참조값을 넣어준다. 
	@RequestMapping("/fortune3")
	public ModelAndView fortune3(ModelAndView mView) {
		
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		
		//HttpServletRequest 객체 대신에 ModelAndView 객체를 생성해서
		//ModelAndView mView = new ModelAndView();
		//위에서 매개변수 처리 했기 때문에 따로 new 해줄 필요 없다.
		
		// view page에 전달할 내용을 담는다
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담는다
		mView.setViewName("fortune");
		//모델과 view page 정보가 모두 담겨 있는 ModelAndView 객체를 리턴해주면 똑같이 동작한다.
		return mView;
	}
}
