package com.example.boot07.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.gallery.dao.GalleryDao;
import com.example.boot07.gallery.dto.GalleryDto;

//컨트롤러 매소드에서 리턴한느 데이터가 바로 클라이언트에게 응답되는 컨트롤러(원래는 뷰페이지 위치..)

@RestController //여기서 리턴하는 문자열이 클라이언트에게 바로 출력( ResponseBody가 포함된 것)
public class AndroidController {
	
	
	@Autowired
	private GalleryDao galleryDao;
	
	@GetMapping("/android/gallery/list")
	public List<GalleryDto> galleryList () {
		//20개만 select해 오도록 GalleryDto에 정보 담기
		GalleryDto dto = new GalleryDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(20);
		//GalleryDao 객체가 리턴해주는 데이터를 바로 리턴해주기
		return galleryDao.getList(dto); // [ {}, {}, {}, ...]
	}
	
	
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	/*
	 *  string 타입을 리턴하면 리턴된 String의 내용이 그대로 응답된다.
	 */
	@GetMapping("/android/tweet")
	//@ResponseBody생략 가능 @RestController이기 때문에
	public String tweet(String msg) { //주소창에서 msg를 치면 파라미터는 자동 추출됨. 
		System.out.println("안드로이드에서 전송된 문자열:" + msg);
		return "Success!";
		}
	
	/*
	 * Map 타입을 리턴하면 Map에 담긴 내용이 {}형식의 json 문자열로 응답된다. --(안드로이드) --> JSONObject를 만들어서 그 안에있는 문자열을 추출해야함.
	 */
	@PostMapping("/android/tweet2") 
	public Map<String, Object> tweet2(String msg) {
		System.out.println("안드로이드에서 전송된 문자열:" + msg);
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		return map; //@RestController안에서 map이 return되면 JSON문자열이 리턴된다. --> 안드로이드안에서 어떻게 제이슨문자열을 받는지 보자.
		
	}
	
	/*
	 * List타입을 리턴하면 List에 담긴 내용이 []형식의 json문자열로 응답된다. --(안드로이드) --> JSONArray
	 */
	@GetMapping("/android/tweet3")
	public List<String> tweet3(String msg) {
		System.out.println("안드로이드에서 전송된 문자열:" + msg);
		
		List<String> names = new ArrayList<String>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		
		return names; // [ ] JSON형식이 리턴이 됨 --> 안드로이드에서 어떻게 이 제이슨 문자열을 받을지 확인해보자
	}
	
	//테스트를 위해서 CafeDao 주입받기
	@Autowired
	private CafeDao dao;
	
	@GetMapping("/android/list")
	//1 페이지의 내용을 selelct 해오기 위한 CafeDto객체를 준비
	public List<CafeDto> list() {
		CafeDto dto = new CafeDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(10);
		
		List<CafeDto> list = dao.getList(dto);
		return list;
	}
}
