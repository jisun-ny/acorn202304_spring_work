package com.gura.spring04.gallery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.file.dto.FileDto;

public interface GalleryService {
	//파일 목록 얻어오기
	public void getList(HttpServletRequest resquest);
	//업로드 된 이미지 파일 저장하기
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request);
	//이미지 파일 하나의 정보 얻어오기
	public void getFileData(int num, ModelAndView mView);
	//파일 삭제하기
	public void deleteFile(int num, HttpServletRequest request);
	
}
