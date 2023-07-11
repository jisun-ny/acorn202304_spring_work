package com.gura.step01.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //이걸 입력해주면 얘를 컨트롤러로 사용할 수 있다. //annotation으 붙이면 런타임시에 이 클래스를 수정해서 컴파일해서 사용
public class MemberController {
	
	@RequestMapping("/member/delete")
	public String delete(int num) {
		//spring 안에선 request.getParameter할 필요없이 괄호안에 넣어주면 자동으로 추출되서 전달이 됨.
		System.out.println(num+" 번 회원을 삭제합니다.");
		/*
		 * redirect로 응답을 할 때는 "redirect: 새로 요청할 경로" 형식으로 view page 정보를 작성하면 된다.
		 */
		return "redirect:/"; // 그냥 "/" 하면 최상위 경로(home)로 리다이렉트 됨. 
		//컨트롤러를 타긴 했지만!! 응답을 두번 함 
	}
	
	// "/member/insertform" 요청을 처리할 메소드 만들기
	@RequestMapping("/member/insertform") //이 메소드가 어떤 요청을 처리할지 //member의 insertform을 응답할것임
	public String insertform() {
		// 응답할 jsp 페이지의 위치를 리턴해주면 된다.
		return "member/insertform"; //여기는 앞에 /가 붙으면 안됨! 자동으로 WEB-INF/가 앞에 붙기 떄문에
	}
	
	/*
	 *	[ 요청 파라미터를 추출하는 방법 1 ]
	 * 	HttpServletRequest 객체를 Controller 메소드로 전달받아서 직접 추출한다.
	 */
	
	@RequestMapping("/member/insert")
	public String insert(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8"); //인코딩 필터로 설정하자!
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		
		System.out.println(num+"|"+name+"|"+addr);
		return "member/insert";	
	}
	
	
	   /*
	    *  [ 요청 파라미터 추출하는 방법 2 ]
	    *  
	    *  파라미터명과 동일하게 메소드의 매개변수를 선언해 놓으면 자동으로 추출해서 넣어준다.
	    *  
	    *  <input name="num">  이면  int num or String num
	    *  <input name="email"> 이면  String email  이런 식으로 선언 하면 된다. 
	    */
	@RequestMapping("/member/insert2")
	public String insert2(int num, String name, String addr) {
		//int type에 숫자가 아니라 다른것을 입력하면 400 잘못된 요청 오류뜸 
		System.out.println(num+"|"+name+"|"+addr);
		return "member/insert";
	}
	
    /*
     *  [ 요청 파라미터 추출하는 방법 3 ]
     *  
     *  파라미터명과 동일한 필드명을 가지고 있는 dto 클래스 type 을  메소드의 매개변수로 선언해 놓으면
     *  자동으로 추출해서 dto 에  추출한 값을 setter 메소드를 이용해서 넣은 다음  해당 dto 객체의 
     *  참조값이 전달된다.
     *  
     *  pulic class MemberDto{
     *     private int num;  => <input name="num">
     *     private String name; => <input name="name">
     *     private String addr; => <input name="addr">
     *  }
     *  
     */
	@RequestMapping("/member/insert3")
	public String insert3 (MemberDto dto) {
		// 이렇게 하면 내부적으로 new MemberDto 되고 setNum... 해서 한번에 추출 할 수 있음.
		
		System.out.println(dto.getNum()+"|"+dto.getName()+"|"+dto.getAddr());
		return "member/insert3";
	}
	
}

