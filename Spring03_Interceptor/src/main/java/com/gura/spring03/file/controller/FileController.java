package com.gura.spring03.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring03.file.dto.FileDto;

/*
 *   [ spring mvc 파일 업로드 처리 ]
 *   
 *   1. pom.xml 에  commons-io, commons-fileupload 가 dependency 에 명시 되어 있어야 한다.
 *   2. servlet-context.xml 에  MultipartResolver bean 설정이 있어야한다.
 *   3. MultipartFile 객체를 컨트롤러에서 받아서 사용하면 된다.
 *       
 */

@Controller
public class FileController {
	//이미지 업로드 요청 처리
	@ResponseBody //제이슨을 리턴.
	@RequestMapping(method = RequestMethod.POST, value="/image/upload")
	public Map<String, Object> imageUpload(MultipartFile image, HttpServletRequest request) {
		// 업로드 처리를 하고 {"imagePath" : "/resource/upload/xxxxxx.jpg} 이런 제이슨 문자열을 응답할 것이다. (제이슨 문자열을 리턴받으려고 map을 사용하고 @ResponseBody 해준것이다..)
		// 								이 주소를 활용해서 <img src=" ">에 넣어줌
		
		//1. 원본 파일명(String type으로 얻어 내 수 있다)
	      String orgFileName=image.getOriginalFilename();
	    
	      
	      // webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      //저장할 파일의 상세 경로
	      String filePath=realPath+File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload=new File(filePath);
	      if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
	         upload.mkdir(); //만들어 준다.
	      }
	      //저장할 파일 명을 구성한다.
	      String saveFileName=
	            System.currentTimeMillis()+orgFileName;
	      // System.currentTimeMillis()을 붙이는 이유는 파일명이 겹칠 수 있으니까 순간순간 계속 달라지는 숫자를 원본 파일명 앞에 붙여서 파일명을 겹치지 않게 해줌
	      try {
	         //3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
	    	  image.transferTo(new File(filePath+saveFileName)); //옮길 목적지와 저장된 파일명을 가지고 있는 file객체를 넣어줘서 이동시킴
	         System.out.println(filePath+saveFileName); //콘솔에서 파일 경로와 파일 이름을 한번에 확인할 수 있다.
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	     // Map 객체에 
	      Map<String, Object> map = new HashMap<>();
	      //"imagePath"라는 키값으로 업로드된 이미지를 요청할 수 있는 경로를 담아서
	      map.put("imagePath", "/resources/upload/"+saveFileName);
	      //리턴해주면 {"imagePath" : "xxx"} 형식의 json 문자열이 응답된다.
	      return map;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/file/upload2")
	public String upload2(FileDto dto, HttpServletRequest request) {
		//FileDto 필드에 MultipartFile 객체의 참조값이 들어있다.
		
		//FileDto객체에 들어있는 MultipartFile객체를 이용해서 파일 업로드 관련 처리를 한다.
		MultipartFile myFile = dto.getMyFile();
		
		//하고 밑에 내용은 동일
		//1. 원본 파일명(String type으로 얻어 내 수 있다)
	      String orgFileName=myFile.getOriginalFilename();
	      //2. 파일의 크기(long type으로 얻어 내 수 있다)
	      long fileSize=myFile.getSize();
	      
	      // webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      //저장할 파일의 상세 경로
	      String filePath=realPath+File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload=new File(filePath);
	      if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
	         upload.mkdir(); //만들어 준다.
	      }
	      //저장할 파일 명을 구성한다.
	      String saveFileName=
	            System.currentTimeMillis()+orgFileName;
	      // System.currentTimeMillis()을 붙이는 이유는 파일명이 겹칠 수 있으니까 순간순간 계속 달라지는 숫자를 원본 파일명 앞에 붙여서 파일명을 겹치지 않게 해줌
	      try {
	         //3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
	         myFile.transferTo(new File(filePath+saveFileName)); //옮길 목적지와 저장된 파일명을 가지고 있는 file객체를 넣어줘서 이동시킴
	         System.out.println(filePath+saveFileName); //콘솔에서 파일 경로와 파일 이름을 한번에 확인할 수 있다.
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
		return "file/upload";
		
	}
	
	
	/*
	 * 파일 업로드 요청 처리
	 * title이라는 파라미터 명으로 제목(파일의 설명)
	 * myFile이라는 파라미터 명으로 첨부파일이 전송된다.
	 */
	
	@RequestMapping(method= RequestMethod.POST, value = "/file/upload")
	public String upload(String title, MultipartFile myFile, HttpServletRequest request) {
		/*
		 * 입력한 제목은 title에 들어있고, 업로드 된 파일에 대한 정보는 myFile 객체에 들어있다.
		 * 따라서 myFile 객체의 메소드를 활용해서 업로드 처리에 필요한 정보를 얻어내고 어떤 동작을 수행하면 된다.
		 * 참고로 일반적인 웹서버 application은 클라이언트가 파일 업로드를 해오면 운영 체제의 임시폴더에 
		 * 이상한(긴) 파일명으로 일단 저장을 해 놓는다.
		 * 그 이후에 임시 폴더에 저장되었던 파일은 운영체제가 자동으로 지우거나 비운다.
		 * 따라서 해당 파일을 사용하기 위해서는 원하는 위치로 이동(copy) 시켜야 한다.
		 * webapp에 resources은 클라이언트가 직접 요청으 통해서 받아갈 수 있는 폴더. 여기에 업로드 폴더를 만들어서 저장할 것이다.
		 */
			//보통 이런 긴 작업은 컨트롤러에서 하지 않고  service에서 해줌.
	      //1. 원본 파일명(String type으로 얻어 내 수 있다)
	      String orgFileName=myFile.getOriginalFilename();
	      //2. 파일의 크기(long type으로 얻어 내 수 있다)
	      long fileSize=myFile.getSize();
	      
	      // webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      //저장할 파일의 상세 경로
	      String filePath=realPath+File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload=new File(filePath);
	      if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
	         upload.mkdir(); //만들어 준다.
	      }
	      //저장할 파일 명을 구성한다.
	      String saveFileName=
	            System.currentTimeMillis()+orgFileName;
	      // System.currentTimeMillis()을 붙이는 이유는 파일명이 겹칠 수 있으니까 순간순간 계속 달라지는 숫자를 원본 파일명 앞에 붙여서 파일명을 겹치지 않게 해줌
	      try {
	         //3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
	         myFile.transferTo(new File(filePath+saveFileName)); //옮길 목적지와 저장된 파일명을 가지고 있는 file객체를 넣어줘서 이동시킴
	         System.out.println(filePath+saveFileName); //콘솔에서 파일 경로와 파일 이름을 한번에 확인할 수 있다.
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
		return "file/upload";
		
	}
	
	
	@RequestMapping("/file/insertform")
	public String insertform() {
		// /WEB-INF/views/file/insertform.jsp 페이지로 forward 이동해서 응답
		return "file/insertform";
	}
	
	
}
