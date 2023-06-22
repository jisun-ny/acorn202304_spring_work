package com.gura.spring02.member.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.member.dto.MemberDto;

//Dao와 Mapper사이의 관계
//Mapper에 sql작성해놓고 session. 통해서 불러와서 사용


//구냥 빈으로 관리되기만 하면 되는 애들은 @Component //특별한 애들은 @Repositoty @Service @Controller
//component scan시 bean이 되기 위해 (com.gura.spring02 하위폴더는 다 스캔 됨)
@Repository //dao는 @Repository 어노데이션을 보통 붙임
public class MemberDaoImpl implements MemberDao{ //implement하여서 추상 메소드를 오버라이드
	//MyBatis를 사용하기 위한 핵심 의존 객체 주입 받기
	//주입받으려면 다 스프링 빈으로 관리되고 있어야함. 빈인 애들끼리..
	@Autowired //이렇게 해놓으면 dao가 사용되는 그 시점에 spring이 알아서 넣어줌
	private SqlSession session;
			//session은 SqlSessionTemplate type인데 SqlSession타입으로 받아서 사용
	//원래 필드는 선언만하면 null이지만 오토 와이어드 됐기떄문에 호출했을 떄 값이 들어옴

	@Override
	public void insert(MemberDto dto) {
		//회원 한명의 정보를 저장
		session.insert("member.insert", dto);
		//sql문은 membermapper에서 작성
		
	}

	@Override
	public void update(MemberDto dto) {
		session.update("member.update", dto);
		
	}

	@Override
	public void delete(int num) {
		session.delete("member.delete", num);
		
	}

	@Override
	public MemberDto getData(int num) {
		return session.selectOne("member.getData", num);	 
	}

	@Override
	public List<MemberDto> getList() {
		//회원 목록을 얻어와서
		List<MemberDto> list = session.selectList("member.getList");
												//mapper의 namespace
		//session.selectOne(statement) //select된 row가 1개라면 굳이 list에 담아올 필요가 없다. 
		//리턴해주기
		return list;
	}

}
