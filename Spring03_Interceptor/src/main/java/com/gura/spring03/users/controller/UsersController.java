package com.gura.spring03.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

@Controller
public class UsersController {
	   //로그인 폼 요청 처리
	   @RequestMapping("/users/loginform")
	   public String loginform() {
	      return "users/loginform";
	   }
	   //로그인 요청 처리
	   @RequestMapping("/users/login")
	   public String login(String id, HttpSession session){ 
		   //파라미터로 전달되는 id를 session scope에 저장해서 가상의 로그인 처리를 한다.
		   //세션을 선언해주면 스프링이 알아서 넣어줌!
	      session.setAttribute("id", id);
	      return "users/login";
	   }
	   //로그아웃 요청 처리
	   @RequestMapping("/users/logout")
	   public String logout(HttpSession session) {
		   //session scope에 저장된 모든 내용을 clear해서 로그아웃 처리를 한다.
	      session.invalidate();
	      return "users/logout";
	   }
	   //개인정보 보기 요청처리
	   @RequestMapping("/users/info")
	   public String info() {
	      return "users/info";
	   }
}
