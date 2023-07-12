package com.example.boot06;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HomeController {

   @GetMapping("/")
   public String home(HttpServletRequest request) {
                        
      List<String> noticeList = new ArrayList<>();
      noticeList.add("SpringBoot를 시작합니다");
      noticeList.add("공지사항1...");
      noticeList.add("공지사항2...");
      
      request.setAttribute("noticeList", noticeList);
      
      return "home";
   }
  
}