package com.PS29729.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/", "/home", "/home/index"})
	public String index() {
		return "redirect:/product/home";
	}
	
	@RequestMapping("/admin/home/index")
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	@RequestMapping({"/list"})
	public String list() {
		return "redirect:/product/list";
	}
}
