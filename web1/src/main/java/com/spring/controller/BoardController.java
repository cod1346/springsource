package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Controller @RequestMapping("/board") @Slf4j
public class BoardController {
	
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read() {
		log.info("read 요청");
	}
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 요청");
	}
	@PostMapping("/register")

	//	public String registerPost(BoardDTO dto,RedirectAttributes rttr) {
//		log.info("register post 요청"+dto);
////		rttr.addAttribute("name",dto.getName());
////		rttr.addAttribute("password",dto.getPassword());
////		rttr.addAttribute("title",dto.getTitle());
////		rttr.addAttribute("content",dto.getContent());
//		
//		rttr.addFlashAttribute("boardDTO", dto);
//		
//		return "redirect:/board/read";
//	}

	
	public void registerPost(@ModelAttribute("dto") BoardDTO dto,RedirectAttributes rttr) {
		log.info("register post 요청"+dto);
		
		
	
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modify() {
		log.info("modify 요청");
	}
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public void remove() {
		log.info("remove 요청");
	}
	
	
}
