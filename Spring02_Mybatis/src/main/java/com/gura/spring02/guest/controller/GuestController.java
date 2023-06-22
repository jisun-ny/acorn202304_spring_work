package com.gura.spring02.guest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.guest.dao.GuestDao;
import com.gura.spring02.guest.dto.GuestDto;
import com.gura.spring02.guest.service.GuestService;
;

@Controller
public class GuestController { 
	//컨트롤러는 비지니스 로직을 대신 처리해주는 서비스에 의존한다.
	@Autowired
	private GuestService service;
//	@Autowired
//	private GuestDao dao;
	//controller가 필요로 하는 핵심 의존 객체. 
	//원래 컨트롤러는 dao에 의존을 잘 하지 않음. 원래는 service에 의존을 해야함..
	//controller는 service에 의존. service는 dao에 의존
	//로직처리를 서비스한테 맡겨서 최대한 컨트롤러를 단순화
	//예를들어 페이징처리에 관한 메소드는 길어질 수 밖에없음. 이런 로직을 서비스에서함.
	@RequestMapping(method = RequestMethod.POST, value = "/guest/update")
	public String update (GuestDto dto) {
//		dao.update(dto); //로직처리(어떤 서비스로 로직을 처리하고)
		//서비스를 이용하여 글 정보를 추가한다.
		service.addGuest(dto);
		return "guest/update"; //(어디로 이동할지)
		
	}
	
	@RequestMapping("/guest/updateform")
	public ModelAndView updateform (ModelAndView mView, int num) {
		//서비스를 이용해서 ModelAndView객체에 글 하나의 정보를 담아온다.
		//ModelAndView객체의 참조값 
		service.getGuestInfo(mView, num); //자동으로 에드오브젝트 됨. 컨트롤러에선 뷰페이지 정보만 전달햐주면 됨.
		//ModelAndView객체의 참조값 에 정보가 저장되고
		//수정할 글 정보를 얻어와서
//		GuestDto dto = dao.getData(num);
//		//ModelAndView 객체에 담고
//		mView.addObject("dto", dto);
		//view page로 forward 이동해서 수정 폼 응답하기
		mView.setViewName("guest/updateform"); //ModelAndView객체의 참조값에 컨트롤러가 여기서 또 view페이지 정보를 담고
		return mView; ////ModelAndView객체의 참조값을 리턴
	}
	
				//post method인 경우는 method를 표시해 주는 것이 좋다.
	@RequestMapping(method = RequestMethod.POST, value = "/guest/delete")
	public String delete(GuestDto dto) {
						//num과 pwd가 전달
		//dao.delete(dto);
		service.deleteGuest(dto);
		return "redirect:/guest/list";
	}
	
				//포스트 방식의 메소드가 요청됐을 때만 value가 작동한다.)
	@RequestMapping(method = RequestMethod.POST, value="/guest/insert")
	public String insert (GuestDto dto) {
							//이렇게 선언하는 것 만으로도 writer, content, pwd가 자동으로 추출되어서 전달이 됨.
		//전달받은 데이터를 dao를 이용하여 잘 저장하자
		service.addGuest(dto);
		//dao.insert(dto);
		return "guest/insert";
	}
	
	@RequestMapping("/guest/insertform")
	public String insertform () {
		return "guest/insertform";
	}
	
//	@RequestMapping("/guest/list")
//	public String list (HttpServletRequest request) {
//		List<GuestDto> list = dao.getList();
//		request.setAttribute("list", list);
//		return "guest/list";
//	}
	
	@RequestMapping("/guest/list")
	public ModelAndView list (ModelAndView mView) {
		//방명록 목록 얻어오기
//		List<GuestDto> list = dao.getList();
		//서비스  메소드를 호출해서 ModelAndView 객체의 참조값을 전달하면 방명록 목록이 담긴다.
		service.getGuestList(mView);
		//value type
		//ModelAndView 객체에 담고
//		mView.addObject("list", list);
						//key값
		//view page 정보도 담고
		mView.setViewName("guest/list");
		/*
		 * 두개의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면
		 * addObject("key", value)를 통해서 담은 정보는 request scope에 담기고
		 * mView.setViewName(view page 위치)를 통해서 담은 정보는 해당 view page로 forward이동해서 응답된다.
		 * --> 이 것들을 spring framework가 해준다.
		 */
		// 어떤 키값을 어떤 타입을 담았는지 기억하고있다가 jsp페이지로 이동해서 코딩해야함!! 담은게 list라면 제너릭타입도 기억을 하고 가야함!
		return mView;
	}
}
