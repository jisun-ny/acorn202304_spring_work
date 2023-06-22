package com.gura.spring02.guest.dao;

import java.util.List;

import com.gura.spring02.guest.dto.GuestDto;

//인터페이스는 구현할 메소드의 리턴타입을 강제하는 것.
public interface GuestDao {
	public void insert(GuestDto dto); //GuestDto를 인자로 전달받아 삽입
	public void update(GuestDto dto);
	public void delete(GuestDto dto); //디티오 가져아야함  //삭제할 번호를 전달받아 삭제 + 비밀번호도 가져와야하니까
	public GuestDto getData(int num); 
	public List<GuestDto> getList(); //리턴타입은 List
}
