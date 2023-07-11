package com.example.boot03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@ResponseBody //return하는 문자열이 client에게 바로 갈 수 있게 하기 위해서
	@RequestMapping("/hello")
	public String hello() {
		return "Nice to meet you!";		
	}
}
