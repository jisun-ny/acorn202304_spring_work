package com.example.boot07.file.controller;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.file.Dto.FileDto;
import com.example.boot07.file.service.FileService;


@Controller
public class FileController {
	
	@Autowired
	private FileService service; 
	
	   @GetMapping("/file/list")
	   public String list(HttpServletRequest request) {
	      
	      service.getList(request);
	      
	      return "file/list";
	   }
	   
	   @GetMapping("/file/upload_form")
	   public String uploadForm() {
	      
	      return "file/upload_form";
	   }
	   
	   //파일 업로드 요청 처리
	   @PostMapping("/file/upload")
	   public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		   //FileDto에는 폼 전송되는 title과 myFile이 들어있다.
	      service.saveFile(dto, mView, request);
	      mView.addObject("file/upload");
	      return mView;
	   }
	   
	   //다운로드 해줄 파일의 번호가 요청 파라미터로 전달된다.
	   @GetMapping("/file/download")
	   public ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException {
		   //다운로드해줄 파일의 번호를 서비스에 전달을 해서 ResponseEntity객체를얻어내서  리턴해준다.
		   return service.getFileData(num);
	   }
	   
	   @GetMapping("/file/delete")
	   public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
	      service.deleteFile(num, request);
	      mView.setViewName("redirect:/file/list");
	      return mView;
	   }
}
