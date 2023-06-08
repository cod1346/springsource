package com.spring.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BookDTO;
import com.spring.domain.SearchDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller @RequestMapping("/book") @Slf4j
public class BookController {

	@Autowired
	private BookService service;
	
//	@GetMapping("/insert")
//	public String insertForm() {
//		log.info("도서 정보 추가 폼 요청");
//		return "/product/insert";
//	}
//	
//	@PostMapping("/insert")
//	public String insertPost(BookDTO dto) {
//		log.info("도서 정보 추가");
//		try {
//			boolean insertFlag = service.insert(dto);
//			if(insertFlag) {
//				return "redirect:/book/list";
//			}else {
//				return "/product/insert";
//			}
//			
//		} catch (Exception e) {
//			System.out.println("오류");
//			return "/product/insert";
//		}
//	}
//	
//	@GetMapping("/list")
//	public String listForm(Model model) {
//		log.info("도서정보목록 요청");
//		
//		List<BookDTO> list=service.getList();
//		
//		model.addAttribute("list", list);
//		
//		return "/product/list";
//	}
//	
//	@GetMapping("/read")
//	public String readGet(int code,Model model) {
//		log.info("도서조회 "+code+" 요청");
//		
//		model.addAttribute("dto", service.get(code));
//		return "/product/read";
//	}
	@GetMapping("/insert")
	public void insertForm() {
		log.info("도서 정보 추가 폼 요청");
	}
	@PostMapping("/insert")
	public String insertPost(BookDTO dto) {
		log.info("도서 정보 추가");
		try {
			boolean insertFlag = service.insert(dto);
			if(insertFlag) {
				return "redirect:/book/list";
			}else {
				return "/book/insert";
			}
		} catch (Exception e) {
			System.out.println("오류");
			return "/book/insert";
		}
	}
	@GetMapping("/list")
	public void listForm(Model model) {
		log.info("도서정보목록 요청");
		List<BookDTO> list=service.getList();
		model.addAttribute("list", list);
	}
	@GetMapping({"/read","/modify"} )
	public void readGet(int code,Model model) {
		log.info("도서조회 "+code+" 요청");
		model.addAttribute("dto", service.get(code));
	}
	@PostMapping("/modify")
	public String updatePost(BookDTO dto,RedirectAttributes rttr) {
		log.info("도서가격 수정 요청"+dto);
		rttr.addAttribute("code",dto.getCode());
			if(service.update(dto)) {
				return "redirect:/book/read";
			}else {
				return "/book/modify";
			}
	}
	@GetMapping("/remove")
	public String removeGet(int code) {
		log.info("도서삭제 요청"+code);
		if(service.delete(code)) {
			return "redirect:/book/list";
		}else {
			return "/book/modify";
		}
	}
//	@GetMapping("/search")
//	public String searchGet(@Param("criteria")String criteria,@Param("keyword") String keyword,Model model) {
//		log.info("도서검색 요청 "+criteria+", "+keyword);
//		List<BookDTO> list = service.getSearchList(criteria, keyword);
//		model.addAttribute("list", list);
//		return "/book/list";
//	}
	@GetMapping("/search")
	public String searchGet(SearchDTO dto,Model model) {
		log.info("도서검색 요청 "+dto.getCriteria()+", "+dto.getKeyword());
		List<BookDTO> list = service.getSearchList(dto.getCriteria(), dto.getKeyword());
		model.addAttribute("list", list);
		model.addAttribute("searchDTO",dto);
		return "/book/list";
	}

}
