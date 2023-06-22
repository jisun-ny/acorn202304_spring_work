package com.gura.spring02.guest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.guest.dto.GuestDto;


//context.xml 스프링이 빈으로 관리(객체를 생성해서 spring bean container에 넣는다)할 것들의 정보를 작성해놓은 것.
//component scan이 일어날 때 annotation을 인식해서 빈으로 관리.
//빈이된 객체들의 특ㄱ권 -> 자기가 필요로하는 객체가 있음ㄴ(의존객체) 필드로 선언하고 자동으로 주입 받아서 쓸 수 있다(같은 빈들끼리만)
//mybatis)를 활용해 db연동을 하려면 SqlSession이 필요함. SqlSession도 빈이어야 함 --> 그래서 우리가 context.xml에 명시해놨음.(bean 설정으로 bean이 됨) //또한 GuestDaoImpl에 @Repository을 붙여 (어노테이션을 통해 빈으로 만들어놨기 )떄문에 빈끼리니까!
//mybatis 기반으로 디비 연동을 하기 위한 핵심 의존객체를 Dependency Injection받는다. --> @Autowired 를붙이면 자동으로 객체가 주입이 된다. (from spring bean container)
//우리가 직접 만드는 클래스들은 어노테이션을 통해 빈을 만들고
//우리가 생성하는 클래스가 아닌것들은 빈 설정을 통해 빈으로 만든다..
@Repository
public class GuestDaoImpl implements GuestDao{
	//이 객체를 필요로하는 곳에서 GuestDaoImpl type을 사용하는 것이 아니라 
	//GuestDao type을 사용하게 함. (인터페이스타입을 받아서 사용하게 함. 그러면 Impl클래스의 의존도 down)
	
	@Autowired // -> 실행시점에 얘가 setter method를 만들고 실행시켜서 클래스를 변경시켜줌
	private SqlSession session;

	@Override
	public void insert(GuestDto dto) {
		session.insert("guest.insert", dto);
					//여기서 알 수 있는 3가지 정보
					/*
					 *  mapper의 namespace는 guest
					 *  그 속 sql의 id는 insert
					 *  parameterType => GuestDto
					 */
		
	}

	@Override
	public void update(GuestDto dto) {
		session.update("guest.update", dto);
		//true or false를 리턴하고 싶으면 
		/*
		 * int rowCount = session.update("guest.update", dto);
		 * if(rowCount > 0) {
					return true;
				} else {
					return false;
				}
		 */

		
	}

	@Override
	public void delete(GuestDto dto) {
		session.delete("guest.delete", dto);
		
	}

	@Override
	public GuestDto getData(int num) {		
		return session.selectOne("guest.getData", num);
		/*
		 * resultType이 guestDto이다 라는 사실을 알 수 있음
		 */
	}

	@Override
	public List<GuestDto> getList() {
		
		List<GuestDto> list = session.selectList("guest.getList");
		return list;
		/*
		 * 파라미터타입 없음
		 * resultType이 guestDto이다 라는 사실을 알 수 있음
		 */
	}

}
