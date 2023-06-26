package com.gura.spring04.users.service;

import javax.servlet.http.HttpSession;

import com.gura.spring04.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto); //dto를 전달하면 회원정보를 추가해주는 메소드
	public void loginProcess(UsersDto dto, HttpSession session);
}
