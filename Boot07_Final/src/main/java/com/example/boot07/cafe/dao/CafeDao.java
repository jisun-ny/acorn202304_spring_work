package com.example.boot07.cafe.dao;

import java.util.List;

import com.example.boot07.cafe.dto.CafeDto;


public interface CafeDao {
	//글 목록
	public List<CafeDto> getList(CafeDto dto);
	//글의 갯수
	public int getCount(CafeDto dto);
	//글 추가
	public void insert(CafeDto dto);
	//글 정보 얻어오기
	public CafeDto getData(int num); //메소드 오버로딩: 같은 메소드 명으로 다중 정의 되어져 있다
	//키워드를 활용한 글 정보 얻어오기 (키워드에 부합하는 글 중에서 이전글, 다음글의 글 번호도 얻어올 수 있도록)
	public CafeDto getData(CafeDto dto);
	//조회수 증가 시키기
	public void addViewCount(int num);
	//글 삭제
	public void delete(int num);
	//글 수정
	public void update(CafeDto dto);

}
