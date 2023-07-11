package com.example.boot04;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("Spring Boot 시작입니다");
		noticeList.add("어쩌고");
		noticeList.add("저쩌고");
		
		request.setAttribute("noticeList", noticeList);
		return "home";
	}
	
	@GetMapping("/fortune")
	public ModelAndView fortune(ModelAndView mView) {
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		mView.addObject("fortuneToday", fortuneToday);
		mView.setViewName("fortune");
		//리턴해준다.
		return mView;
	}
	
	@GetMapping("/fortune2")
	public String fortune2(HttpServletRequest request) {
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		request.setAttribute("fortuneToday", fortuneToday);
		return "fortune";
	}

}
