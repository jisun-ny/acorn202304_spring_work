package com.gura.spring02.member.dao;

import java.util.List;

import com.gura.spring02.member.dto.MemberDto;

public interface MemberDao {
	//boolean type으로 리턴하는거는 나중에 다르게 관리할 것이기 때문에 일단 뺀다.
	public void insert(MemberDto dto);
	public void update(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num);
	public List<MemberDto> getList();
	
}
