package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.CustomUserDetailService;

import lombok.extern.slf4j.Slf4j;

@Controller@Slf4j @RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private CustomUserDetailService service;
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인페이지 요청");
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		
		model.addAttribute("error", "아이디나 비밀번호를 확인해 주세요");
		return "/member/login";
	}
	
	@GetMapping("/admin")
	public void adminGet() {
		log.info("어드민 로그인");
	}
	
	@GetMapping("/auth")
	@ResponseBody
	public Authentication auth() {
		log.info("memberauth");
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
