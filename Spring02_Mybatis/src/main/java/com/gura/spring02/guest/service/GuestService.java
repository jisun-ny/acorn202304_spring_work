package com.gura.spring02.guest.service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.guest.dto.GuestDto;

public interface GuestService {
	public void addGuest(GuestDto dto);
	public void updateGuest(GuestDto dto);
	public void deleteGuest(GuestDto dto);
	public void getGuestInfo(ModelAndView mView, int num);
	//모델엔 뷰랑 글정보를 전달하면 글 번호에 맞는 정보를 모델엔 뷰에 넣어주는 메소드
	public void getGuestList(ModelAndView mView);
	//모델앤뷰의 참조값을 줄테니 이 모델엔뷰에다가 글목록을 담아줘
}
