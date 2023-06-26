package com.gura.spring04.users.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gura.spring04.users.dao.UsersDao;
import com.gura.spring04.users.dto.UsersDto;

@Repository
public class UsersServiceImpl implements UsersService {
	
	//보통 service는 dao에 의존함.
	@Autowired
	private UsersDao dao;

	@Override
	public void addUser(UsersDto dto) {
		//비밀번호를 암호화해줄 객체를 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //비밀번호 암호화
		//암호화 된 비밀번호를 얻어내서
		String encodedPwd = encoder.encode(dto.getPwd());//CharSequence는 String type을 구현한 것. 스트링 타입을 넣어주면 됨.
		//UserDto 객체에 다시 담고
		dto.setPwd(encodedPwd);
		//UserDao객체를 이용해서 DB에 다시 저장
		dao.insert(dto);
	}

		
		@Override
		public void loginProcess(UsersDto dto, HttpSession session) {
		   //입력한 정보가 맞는지 여부
		   boolean isValid = false;
		   //아이디를 이용해서 회원정보를 얻어옴
		   UsersDto resultDto = dao.getData(dto.getId());
		   //만일 select된 회원정보가 존재하고
		   if(resultDto != null) {
		      //Bcrypt 클래스의 static 메소드를 이용해서 입력한 비밀번호와 암호화해서 저장된 비밀번호 일치여부를 알아냄
		      isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
		      			//실제입력한 비밀번호		//DB에 저장된 (암호화된) 비밀번호  //이 둘은 비교연산자로 비교할 수 없다. 메소드를 사용해서 일치 하는지 안하는지만 알 수 있다.(암호화되어있기 때문에)
		   }
		   
		   //만일 유효한 정보라면
		   if(isValid) {
		      //로그인 처리를 함
		      session.setAttribute("id", resultDto.getId());
		   }
		   
		}

}
