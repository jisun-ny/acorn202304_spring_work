package com.example.boot07.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.example.boot07.cafe.dto.CafeCommentDto;
import com.example.boot07.cafe.dto.CafeDto;



public interface CafeService {
	public void getList(HttpServletRequest request, Model model);// 아예 컨트롤러에서 pageNum을 추출해서 보내줌
	public void getDetail(HttpServletRequest request, Model model);
	public void saveContent(CafeDto dto);
	public void updateContent(CafeDto dto);
	public void deleteContent(int num, HttpServletRequest request);
	public void getData(HttpServletRequest request); //글을 수정하기 위해서 정보 불러오는 기능
	
	
	public void saveComment(HttpServletRequest request);//댓글 저장
	public void deleteComment(HttpServletRequest request); //댓글 삭제
	public void updateComment(CafeCommentDto dto); //댓글 수정
	public void moreCommentList(HttpServletRequest request); //댓글 더보기 기능 //스크롤 쭉 끝까지 내리면 자동으로 페이지 요청해서 가져오는 기능 구현

}
