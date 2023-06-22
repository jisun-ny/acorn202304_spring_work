package com.gura.step01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gura.step01.member.MemberDto;

/*
 * JSON 문자열 응답하는 방법
 * 
 * 1. pom.xml에 jackson-databind dependency를 추가한다
 * 2. 컨트롤러 메소드에 @ResponseBody annotation을 붙여준다.
 * 3. Dto, List, Map 등을 컨트롤러에서 리턴해주면 해당 객체에 담긴 정보가 json으로 구성되어서 응답한다.
 */

@Controller
public class TestController {
	
	@ResponseBody //이렇게 하면 return하는 문자열을 그냥 그대로 출력해주세요라는 의미쓰..
	@RequestMapping("/test/json1") 
	public String json1 () {
		
		return "{\"num\":1, \"name\":\"kim\", \"addr\":\"seoul\"}";
	}
	
	@ResponseBody
	@RequestMapping("/test/json2")
	public MemberDto json () {
		//리턴 타입을 MemberDto로
		
		//DB에서 읽어온 회원 정보라고 가정하자
		MemberDto dto = new MemberDto();
		dto.setNum(2);
		dto.setName("해골");
		dto.setAddr("행신동");
		
		return dto;
	}
	
	@ResponseBody
	@RequestMapping("/test/json3")
	public Map<String, Object> json3 () {
	Map<String, Object> map = new HashMap<>();
	map.put("num", 3);
	map.put("name", "원숭이");
	map.put("addr", "동물원");
	return map;
	}
	
	@ResponseBody
	@RequestMapping("/test/json4")
	public List<String> json4() {
		List<String> names = new ArrayList<>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		
		return names;
	}
	
	@ResponseBody
	@RequestMapping("/test/json5")
	public List<MemberDto> json5() {
		List<MemberDto> list = new ArrayList<>();
		list.add(new MemberDto(1, "김구라", "노량진"));
		list.add(new MemberDto(2, "해골", "행신동"));
		list.add(new MemberDto(3, "원숭이", "동물원"));
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/test/json6")
	public List<Map<String, Object>> json6 () {
		List<Map<String, Object>> list = new ArrayList<>();
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("num", 1);
		map1.put("name", "김구라");
		map1.put("addr", "노량진");
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("num", 2);
		map2.put("name", "해골");
		map2.put("addr", "행신동");
		
		Map<String, Object> map3 = new HashMap<>();
		map3.put("num", 3);
		map3.put("name", "원숭이");
		map3.put("addr", "동물원");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		return list;
		
	}
	//자바는 json에 친화적이지 않기 때문에 이런식으로 
	//dto나 map => {"xxx"} 형태로 리턴 
	//List<String> => ["xxx","xxx"...]
	//List<MemberDto> or List<Map> => 배열 안에 오브젝트가 있는 형태 [{}, {}...]

}
