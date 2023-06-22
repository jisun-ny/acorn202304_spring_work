package com.gura.spring02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao dao; //이렇게 필드에 선언해놓으면 component scan을 통해서 bean이 되어서 보관되다가 필요할때 @autowired된 곳에 넣어줌 DaoImpl을
	
	
	/*
	 * 	@RequestMapping에 method 속성의 값을 명시하지 않으면 요청 방식을 가리지 않고
	 * 	경로만 맞으면 모두 처리해준다 (GET, POST, PUT, DELETE)
	 * 
	 * 	method 속성의 값을 명시하면 경로가 맞더라도 요청 방식이 다르면 처리해 주지 않는다.
	 * 	일반적으로 POST인 경우에는 요청 방식을 명시해 주는 것이 좋다.
	 * 
	 */
	//회원 수정 요청 처리
	@RequestMapping(method = RequestMethod.POST, value = "/member/update")
	public String update( MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	//회원 수정 폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform (ModelAndView mView, int num) {
		//수정할 회원의 정보를 얻어온다
		MemberDto dto = dao.getData(num);
		/*
		 * 수정할 회원의 정보를 ModelAndView 객체의 addObject(key, value) 메소드를 이용해서 담는다.
		 * ModelAndView 객체에 담은 값은 결국 HttpServletRequest 객체에 담긴다( request scope에 담긴다)
		 * 
		 * 원래는 HttpServletRequest request를 받아서
		 * request.setAttribute 해야하는데 이걸 모델앤뷰가 대신해주는 것
		 */
		mView.addObject("dto", dto);
		//view page의 위치도 ModelAndView 객체에 담아서 리턴해야 한다.
		mView.setViewName("member/updateform");
		//모델(data)와 view page의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면
		//spring이 알아서 처리해준다.
		return mView;
	}
	
	/*
	 *	 @RequestParam(value = "파라미터명", defaultValue = "기본값")
	 *	기본값은 없어도 되고 파라미터명과 매개변수명이 일치한다면 생략 가능
	 */
	//회원 삭제 요청 처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam(value = "num", defaultValue = "0") int num) {
					//@RequestParam(value = "num", defaultValue = 0) value num의 값을 가져오고 넘어오지 않았다면 기본값은 0 
					//컨트롤러에다가 파라미터명을 동일하게 설정하면 자동으로 추출해서 가져옴
		//MemberDao 객체를 이용해서 삭제
		dao.delete(num);
		
		//view page로 forward 이동해서 응답
		//return "member/delete";
		
		//목록보기로 redirect 응답
		return "redirect:/member/list";
		//forword는 응답을 위임 //redirect 요청을 다시하라고 강제함.
	}
	
	//회원 추가 요청 처리
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {
		//MemberDao 객체를 이용해서 DB에 저장(핵심 의존객체기 떄문에 바로 new 하지 않고 필드에 선언하고 받아옴)
		dao.insert(dto);
		//view page로 forward 이동해서 응답
		return "member/insert";
	}
	
	//회원 추가 폼 요청 처리
	@RequestMapping("/member/insertform") //여기선 앞에 슬래쉬
	public String insertform() {
		return "member/insertform"; //여기선 앞에 슬래쉬 없음!
		// /WEB-INF/views/member/insertform.jsp 페이지로 forward 이동해서 응답
	}
	
//	//회원 목록 보기 요청 처리( 아직 디에이오를 만들지 못했기때문에 일단 간략하게)
//	@RequestMapping("/member/list")
//	public String list () {
//		// /WEB-INF/views/member/list.jsp 페이지로 forward 이동해서 응답
//		return "member/list";
//	}
	
	//회원 목록 보기 요청 처리
		@RequestMapping("/member/list")
		public String list (HttpServletRequest request) {
			//dao를 이용해서 회원 목록을 얻어와서
			List<MemberDto> list = dao.getList();
			//requestScope에 담고
			request.setAttribute("list", list);
			// /WEB-INF/views/member/list.jsp 페이지로 forward 이동해서 응답
			return "member/list";
		}
}
