package com.gura.spring04.gallery.dao;

import java.util.List;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	//갤러리 목록
	public List<GalleryDto> getList(GalleryDto dto);
	//갤러리 이미지 의 갯수
	public int getCount(GalleryDto dto);
	//갤러리 이미지 추가
	public void insert(GalleryDto dto);
	//이미지 정보 얻어오기
	public GalleryDto getData(int num);
	//이미지 삭제
	public void delete(int num);
	//이미지 수정
	public void update(GalleryDto dto);
	
}
