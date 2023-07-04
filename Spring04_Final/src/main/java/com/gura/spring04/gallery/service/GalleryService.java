package com.gura.spring04.gallery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.file.dto.FileDto;
import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryService {
	//갤러리 리스트 가져오기
	public void getList(HttpServletRequest request);
	//갤러리에 사진 upload & DB 저장하기
	public void saveImage(GalleryDto dto, HttpServletRequest request);
	//갤러리 detail 페이지에 필요한 data를 ModelAndView에 저장
	public void getDetail(ModelAndView mView, int num);
						//번호를 전달하면 해당 번호에 대한 정보를 모델앤 뷰에 담아주는..
	
}
