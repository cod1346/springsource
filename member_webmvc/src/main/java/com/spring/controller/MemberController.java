package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller @RequestMapping("/member") @Slf4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인페이지 요청");
	}
	
	@PostMapping("/login")
	public String loginPost(LoginDTO loginDTO,HttpSession session) {
		log.info("로그인 요청");
		
		AuthDTO authDTO=service.login(loginDTO);
		
		if(authDTO!=null) {
			session.setAttribute("authDTO", authDTO);
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
		
	}
	
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃 요청");
		session.removeAttribute("authDTO");
		return "redirect:/";
	}
	
	@GetMapping("/step1")
	public void stepGet() {
		log.info("약관 페이지 보여주기");
	}
	
	@PostMapping("/step1")
	public String stepPost(boolean agree,RedirectAttributes rttr) {
		log.info("약관동의"+agree);
		
		if(agree) {
			return "/member/register";
		}else {
			rttr.addFlashAttribute("check", false);
			return "redirect:/member/step1";
//			return "/member/step1";
		}
	}
	
	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		log.info("회원가입 요청"+dto);
		
		if(service.register(dto)) {
			return "redirect:/member/login";
		}else {
			return "/member/register";
		}
	}
	
	@PostMapping("/dupId") @ResponseBody
	public String dupIdCheck(String userId) {
		log.info("중복아이디 체크 userid : "+userId);
		System.out.println("123");
		if(service.dupId(userId)) {
			return "true";
		}else {
			return "false";
		}
	}
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴 폼 요청");
	}
	@PostMapping("/leave")
	public String leavePost(LoginDTO loginDTO,HttpSession session) {
		log.info("회원탈퇴 요청 "+loginDTO.getUserId());
		if(service.leave(loginDTO)) {
			session.invalidate();
			return "redirect:/";
		}else {
			return "/member/leave";
		}
	}
	@GetMapping("/changePwd")
	public void changePwdGet(HttpSession session) {
		log.info("비밀번호변경폼 요청");
	}
//	@GetMapping("/changePwd")
//	public String changePwdGet(HttpSession session) {
//		log.info("비밀번호변경폼 요청");
//		if(session.getAttribute("authDTO")==null) {
//			return "redirect:/";
//		}
//		return "/member/changePwd";
//	}
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDTO,HttpSession session) {
		log.info("비밀번호 변경 요청"+changeDTO);
		
		if(changeDTO.passwordEquals()) {
			if(service.update(changeDTO)) {
				session.invalidate();
				return "redirect:/member/login";
			}
		}
		return "redirect:/member/changePwd";
	}
}
