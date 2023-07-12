package com.example.boot06;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
	
	
	@GetMapping("/users/info")
	public String info(Model model) {
		//DB에서 읽어온 개인 정보라고 가정
		String address = "서울시 강남구 삼원빌딩";
		/*
		 * 컨트롤러의 매개변수로 전달된 Model객체에  addAttribute() 메소드를 이용해서
		 * view page에 전달할 내용을 담으면 알아서 HttpServletRequest객체에 담긴다.
		 * 또한 컨트롤러의 return type으로 리턴하지 않아도 동작한다.
		 * 
		 * spring에선 ModelAndView객체를 많이 썼지만 Spring Boot에서는 Model객체를 많이 사용한다.
		 */
		model.addAttribute("address", address);
		//결국 request.setAttribute해준거랑 똑같음
		return "users/info";
	}
	
	//로그아웃 처리
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		//로그아웃 처리
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/users/login")
	public String login(HttpSession session, String id) { //로그인 처리 하려면 세션이 필요함
		//가상의 로그인 처리를 한다.(id만 있으면 로그인 되는 걸로...)
		//session.invalidate(); //세션초기화 --> 초기화 후에는 세션에 정보를 담을 수 없다.
		session.setAttribute("id", id); //아이디는 파라미터로 전달됨, 매소드의 매개변수로 필요한 정보를 받아온다.
		return "redirect:/"; //최상위 경로로 리다이렉트 시켜준다.
	}
	
	@GetMapping("/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	
	
	
}
