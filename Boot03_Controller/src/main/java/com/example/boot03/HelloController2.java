package com.example.boot03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//컨트롤러 메소드에서 리턴하는 내용을 바로 클라이언트에게 응답하는 컨트롤러
//일반 문자열, xml, json 형식의 문자열을 응답할때 주로 사용한다.
//모든 컨트롤러 메소드에 @responseBody 어노테이션이 붙어있다고 생각하면 된다.

@RestController
public class HelloController2 {
	//@responseBody 안써도 문자열이 바로 클라이언트에게 전송됨.(@RestController이기 때문)
	@RequestMapping(method= RequestMethod.GET, value="/hello2")
	public String hello2 () {
		return "hi!";
	}
	
	//GET 방식만 되도록 @GetMapping 사용해서 더 간편하게 작성할 수 있다.
	@GetMapping("/hello3")
	public String hello3 () {
		return "bye!";
	}
	
	/*
	 * @RequestMapping("/hello2")
	 * GET방식(링크 누르기) POST방식 둘 다 되는 것
	 */
}
