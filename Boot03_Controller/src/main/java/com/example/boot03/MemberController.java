package com.example.boot03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@responsebody가 기본
public class MemberController {
	
	//post 방식 /memeber/insert 요청 처리
	@PostMapping("/member/insert")
	public Map<String, Object> insert(String name, String addr) { //httprequest로 추출할 수 있고, 파라미터명으로 추출할 수 있고, dto가 있따면 dto로 추출 가능 //spring에서 한거랑 똑같//여기선 파라미터명으로
		//전송된 파라미터 출력
		System.out.println(name+" | "+addr);
		//json 문자열을 응답하기 위해 Map에 정보를 담아서
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		//Map을 리턴하면 json 문자열이 응답된다.
		return map;
	}
	
}
