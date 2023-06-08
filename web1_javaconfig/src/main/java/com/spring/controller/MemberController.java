package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.LoginDTO;
import com.spring.domain.RegisterDTO;

import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j @RequestMapping("/member")
public class MemberController {
	
//	@RequestMapping("/login")
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void loginGet(HttpServletRequest req) {
		System.out.println("==========================================================");
		log.info("login...");
		log.info("method "+req.getMethod());
		System.out.println("==========================================================");
//		return "/member/login";
	}

//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public void loginPost(HttpServletRequest req) {
//		System.out.println("==========================================================");
//		log.info("login post...");
//		log.info("method "+req.getMethod());
//		System.out.println("id : "+req.getParameter("id"));
//		System.out.println(" : "+req.getParameter("password"));
//		System.out.println("==========================================================");
////		return "/member/login";
//	}
	
//	@RequestMapping("/login")
//	public void loginPost(@RequestParam("id") String id12,String password) {
//		System.out.println("==========================================================");
//		log.info("login post...스트링으로 받기");
//		System.out.println("id : "+id12);
//		System.out.println("password : "+password);
//		System.out.println("==========================================================");
////		return "/member/login";
//	}
	
	
	@PostMapping("/login")
	public String loginPost(LoginDTO dto,Model model) {
		System.out.println("==========================================================");
		log.info("login post...로그인dto로 받기");
		String id = dto.getId();
		model.addAttribute("id",id);
		System.out.println("password : "+dto.getPassword());
		System.out.println("==========================================================");
		return "/member/main";
	}
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
	}
	
	@PostMapping("/register")
	public String registerPost(RegisterDTO dto) {
		System.out.println("==========================================================");
		log.info("회원가입 요청");
		log.info("id : "+dto.getId());
		log.info("password : "+dto.getPassword());
		log.info("name : "+dto.getName());
		log.info("email : "+dto.getEmail());
		System.out.println("==========================================================");
//		return "/member/login"; //http://localhost:8080/member/register
		return "redirect:/member/login"; //http://localhost:8080/member/login
	}
	
	
}
