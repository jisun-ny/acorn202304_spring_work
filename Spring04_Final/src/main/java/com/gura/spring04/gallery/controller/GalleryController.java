package com.gura.spring04.gallery.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GalleryController {
	
	@Autowired
	private service;
	
	@RequestMapping("/gallery/list")
	public String getList(HttpServletRequest request) {
		return "gallery/list";
	}

	@RequestMapping("/gallery/upload_form")
	public String upload_form () {
		return "gallery/upload_form";
	}
	
	@RequestMapping("/gallery/upload")
	public String upload() {
		service.upd
		return "gallery/upload_form";
	}
}
