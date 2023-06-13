package com.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("/list") 
	public void ListGet(@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("list get 요청");

		List<BoardDTO> list = service.getList(cri);

		int total = service.getTotalCnt(cri);

		model.addAttribute("list", list);
		model.addAttribute("pageDTO", new PageDTO(cri, total));
	}

	@GetMapping("/register") @PreAuthorize("isAuthenticated()")
	public void registerGet() {
		log.info("register get 요청");
	}

	@PostMapping("/register") @PreAuthorize("isAuthenticated()")
	public String registerPost(BoardDTO dto, RedirectAttributes rttr, Criteria cri) {
		log.info("register post 요청" + dto);

//		if(dto.getAttachList()!=null) {
//			dto.getAttachList().forEach(attach->log.info("attach : "+attach.toString()));
//		}
		if (service.insert(dto)) {
			log.info("글 번호 :  " + dto.getBno());
			rttr.addFlashAttribute("result", dto.getBno());
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			return "redirect:/board/list";
		}

		return "redirect:/board/register";
	}

	@GetMapping({ "/read", "/modify" })
	public void readGet(int bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("내용조회 요청" + bno);
		BoardDTO dto = service.getRow(bno);
		model.addAttribute("dto", dto);
	}
	
	@PreAuthorize("principal.username == #dto.writer") //로그인 사용자 == 작성자
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes rttr, Criteria cri) {
		log.info("내용수정 요청" + cri);
		if (service.update(dto)) {
			rttr.addFlashAttribute("result", "modify");
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			return "redirect:/board/list";
		}
		return "/board/modify";
	}

	
	@GetMapping("/remove")
	@PreAuthorize("principal.username == #writer") //로그인 사용자 == 작성자
	public String removeGet(int bno,String writer,RedirectAttributes rttr,Criteria cri) {
		
		//폴더에서 첨부 파일 제거
		List<AttachFileDTO> attachList = service.getAttachList(bno);
		deleteAttachFiles(attachList);
		
		//성공 시 리스트
		service.delete(bno);
		
		rttr.addFlashAttribute("result", "delete");
		
		//페이지 나누기 정보 주소줄에 같이 보내기
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		//검색 정보 주소줄에 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";		
	}
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> attachList(int bno) {
		List<AttachFileDTO> attachList = service.getAttachList(bno);

		return attachList != null ? new ResponseEntity<List<AttachFileDTO>>(attachList, HttpStatus.OK)
				: new ResponseEntity<List<AttachFileDTO>>(HttpStatus.NOT_FOUND);
	}

	
	private void deleteAttachFiles(List<AttachFileDTO> attachList) {
		log.info("첨부 파일 폴더에서 제거");
		
		if(attachList == null || attachList.size() <= 0) return;
		
		for(AttachFileDTO dto : attachList) {
			// 파일 경로 
			Path path = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
			
			try {
				Files.deleteIfExists(path);
				
				// 이미지 파일인 경우 썸네일 제거
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
					Files.deleteIfExists(thumb);
				}
				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
}
