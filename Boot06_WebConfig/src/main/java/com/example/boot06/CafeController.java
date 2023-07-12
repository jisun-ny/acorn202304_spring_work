package com.example.boot06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CafeController {
	
	
	
	@GetMapping("/cafe/insertform")
	public String insertform() {
		return "cafe/insertform";
	}
}
