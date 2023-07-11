package com.example.boot05.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot05.dto.MemberDto;

@Repository
public class MemberDao {
	//interface는 생략
	
	@Autowired
	SqlSession session;
	
	public List<MemberDto> getList() {
		return session.selectList("member.getList");
	}
}
