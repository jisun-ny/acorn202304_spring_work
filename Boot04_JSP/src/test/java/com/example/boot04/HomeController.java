package com.example.boot04;

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
   
   @GetMapping("/fortune")
   public ModelAndView fortune(ModelAndView mView) {
      //오늘의 운세
      String fortuneToday = "매우 좋음";
      //ModelAndView 객체에 모델과 뷰페이지 정보를 담음 
      mView.addObject("fortuneToday", fortuneToday);
      mView.setViewName("fortune");
      //리턴해줌
      return mView;
   }
   //위의 ModelAndView와 동일한 역할 
   @GetMapping("/fortune2")
   public String fortune2(HttpServletRequest request) {
      String fortuneToday = "짱 좋음";
      request.setAttribute("fortuneToday", fortuneToday);
      
      return "fortune";
   }
}