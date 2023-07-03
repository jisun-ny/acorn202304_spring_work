package com.gura.spring04.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.cafe.dto.CafeCommentDto;
import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	//댓글 수정 요청 처리(JSON을 응답하도록 한다)
	@RequestMapping("/cafe/comment_update")
	@ResponseBody
	public Map<String, Object> commentUpadate(CafeCommentDto dto) { //map을 사용하면 {}형식이 제이슨 문자열이 응답이 됨
											//dto에 num과 content를 전달
		service.updateComment(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		//{"isSuccess":true} 형식의 json 문자열이 응답되도록 한다.
		return map;
	}
	
	//댓글 삭제 요청 처리
	@RequestMapping("/cafe/comment_delete")
	@ResponseBody
	public Map<String, Object> commentDelete(HttpServletRequest request) {
		service.deleteComment(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		//{"isSuccess":true} 형식의 json 문자열이 응답되도록 한다.
		return map;
	}
	
	//댓글 더보기 요청처리
	@RequestMapping("/cafe/ajax_comment_list")
	public String commentList(HttpServletRequest request) {
		//테스트를 위해 시간 지연시키기
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.moreCommentList(request);
		return "cafe/ajax_comment_list";
	}
	
	@RequestMapping("/cafe/comment_insert")
    public String commentInsert(HttpServletRequest request, int ref_group) {
       //새로운 댓글을 저장하는 로직을 수행
       service.saveComment(request);
       //reg_group은 원글의 글번호이기 때문에 원글 자세히 보기로 다시 리다이렉트 이동됨
       return "redirect:/cafe/detail?num="+ref_group;
    }


	
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@RequestMapping("/cafe/list")
	public String getList(HttpServletRequest request) {
		// service에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
		service.getList(request);
		// view page로 forward 이동해서 응답하기
		return "cafe/list";
	};

	@RequestMapping("/cafe/insertform")
	public String insertform() {
		return "cafe/insertform";
	}

	@RequestMapping("/cafe/insert")
	public String insert(CafeDto dto, HttpSession session) {
		// title, writer 정보가 들어있다.
		// 글 작성자는 세션에서 얻어낸다.
		String writer = (String) session.getAttribute("id");
		// dto 는 글의 제목과 내용만 있으므로 글작성자는 직접 넣어준다.
		dto.setWriter(writer);
		service.saveContent(dto);
		return "cafe/insert";
	}

	@RequestMapping("/cafe/detail")
	public String detail(HttpServletRequest request) {
		// service에 HttpServletRequest객체를 전달해서 응답에 필요한 데이타가 담기도록하고
		service.getDetail(request);
		// view page로 forward이동해서 응답하기.
		return "cafe/detail";
	}

	@RequestMapping("/cafe/delete")
	public String delete(int num, HttpServletRequest request) {
		// service에 삭제할 글 번호와 HttpServletRequest 객체를 전달해서 해당 글을 삭제시키고
		service.deleteContent(num, request);
		// 글 목록 보기로 리다일렉트 이동시킨다.
		return "redirect:/cafe/list";
	}

	@RequestMapping("/cafe/updateform")
	public String updateForm(HttpServletRequest request) {
		service.getData(request);
		return "cafe/updateform";
	}

	@RequestMapping("/cafe/update")
	public String update(CafeDto dto) {
		service.updateContent(dto);
		return "cafe/update";
	}
}