package com.example.boot07.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.users.Dto.UsersDto;

public interface UsersService {
	
	public void addUser(UsersDto dto); --insert
	public void loginProcess(Usersdto dto, HttpSession session) --
	public void getInfo (ModelAndView mView, HttpSession session )
	public void updateUserPwd(Httpsession, usersdto modealdn iew)
	
	
	public void addUser(UsersDto dto); //dto를 전달하면 회원정보를 추가해주는 메소드
	public void loginProcess(UsersDto dto, HttpSession session);   
	public void getInfo(HttpSession session, ModelAndView mView);
    public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView);
    public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile);
    public void updateUser(UsersDto dto, HttpSession session);
    public void deleteUser(HttpSession session, ModelAndView mView);
}
