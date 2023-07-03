package com.gura.spring04.gallery.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.file.dto.FileDto;
import com.gura.spring04.gallery.dao.GalleryDao;
import com.gura.spring04.gallery.dto.GalleryDto;

public class GalleryServiceImpl implements GalleryService{

	@Autowired
	private GalleryDao dao;
	
	@Override
	public void getList(HttpServletRequest request) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum = 1;
		//페이지 번호가 파라미터로 전달된느지 읽어와 본다
		String strPageNum = request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어온다면
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		//보여줄 페이지의 시작 ROWNUM
	      int startPageNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM
	      int endPageNum=pageNum*PAGE_ROW_COUNT;
	      
	      //GalleryDto 객체에 startPageNum과 endPageNum을 담는다
	      GalleryDto dto = new GalleryDto();
	      dto.setStartPageNum(startPageNum);
	      dto.setEndPageNum(endPageNum);
	      
	      //이미지 파일 목록을 select해온다.
	      List<GalleryDto> list = dao.getList(dto);
	      //전체 글의 갯수(검색 키워드가 있는경우 키워드에 부합하는 전체 글의 갯수)
	      int totalRow=dao.getCount(dto);
	      
	
	      
	}

	@Override
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFileData(int num, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
