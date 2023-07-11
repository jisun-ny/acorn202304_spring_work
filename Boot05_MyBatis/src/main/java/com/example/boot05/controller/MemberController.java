package com.example.boot05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot05.dao.MemberDao;
import com.example.boot05.dto.MemberDto;

@Controller
public class MemberController {
	@Autowired MemberDao dao;
	//원래는 service의존해야하지만 간단하게 테스트하기 위해서 MemberDao 바로 주입 받음
	
	@GetMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//회원 목록
		List<MemberDto> list = dao.getList();
		mView.addObject("list", list);
		mView.setViewName("member/list");
	
		return mView;
	}
}
