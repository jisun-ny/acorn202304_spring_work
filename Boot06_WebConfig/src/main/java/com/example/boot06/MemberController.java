package com.example.boot06;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class MemberController {

	@PostMapping("/member/insert")
	@ResponseBody
	public String insert(int num, String name, String addr) {
		
		//MemberDto dto = new MemberDto(num, name, addr);
		
		MemberDto dto = new MemberDto();
		dto.setNum(num);
		dto.setName(name);
		dto.setAddr(addr);
		
		//실제로 아래와 같은 형태로 많이 사용됨
		MemberDto dto2 = MemberDto.builder() //MemberDtoBuilder
				.num(num)
				.name(name)
				.addr(addr)
				.build(); //MemberDto type
		//이렇게 코딩하려면 MemberDto에 @Builder 붙어있어야함
	
		return "ok";
	}
}
