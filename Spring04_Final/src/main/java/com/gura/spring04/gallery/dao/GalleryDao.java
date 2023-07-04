package com.gura.spring04.gallery.dao;

import java.util.List;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	//갤러리 리스트 가져오기
	public List<GalleryDto> getList(GalleryDto dto);
	//모든 row의 갯수
	public int getCount();
	//갤러리에 이미지 저장하기
	public void insert(GalleryDto dto);
	//pk num에 해당하는 DB에서 갤러리 게시글 하나의 data가져오기
	public GalleryDto getData(int num);
	
	
}
