package com.gura.spring04.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.file.dto.FileDto;
import com.gura.spring04.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService service; 
	
	   @RequestMapping("/file/list")
	   public String list(HttpServletRequest request) {
	      
	      service.getList(request);
	      
	      return "file/list";
	   }
	   
	   @RequestMapping("/file/upload_form")
	   public String uploadForm() {
	      
	      return "file/upload_form";
	   }
	   
	   //파일 업로드 요청 처리
	   @RequestMapping("/file/upload")
	   public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		   //FileDto에는 폼 전송되는 title과 myFile이 들어있다.
	      service.saveFile(dto, mView, request);
	      mView.setViewName("file/upload");
	      return mView;
	   }
	   
	   @RequestMapping("/file/download")
	   public ModelAndView download(int num, ModelAndView mView) {
		   							//num은 다운로드 해줄 파일의 번호(PK)
	      service.getFileData(num, mView);
	      // 응답을 할 bean 의 이름을 설정 
	      mView.setViewName("fileDownView"); //스프링이 관리하는 객체 bean에서 직접 다운로드를해줄것임.
	      return mView;			//bean의 이름에서 먼저 찾을지 아니면 /WEB-INF/views/fileDownView.jsp를 찾을지 우선순위가 있어야함.
	      						// 이 설정이 바로  servlet-context.xml
	   }
	   
	   @RequestMapping("/file/delete")
	   public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
	      service.deleteFile(num, request);
	      mView.setViewName("redirect:/file/list");
	      return mView;
	   }
}
