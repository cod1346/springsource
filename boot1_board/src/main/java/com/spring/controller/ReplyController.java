package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@RestController @Slf4j @RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@GetMapping(value="/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno){
		log.info("댓글조회  "+rno);
		return new ResponseEntity<ReplyDTO>(service.read(rno),HttpStatus.OK);
	}
	
	@PostMapping(value="/new") @PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody ReplyDTO dto){
		log.info("댓글등록 "+dto);
		if(service.insert(dto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/pages/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyPageDTO> list(@PathVariable("bno") int bno,@PathVariable("page")int page){
		log.info("댓글조회 "+bno);
		
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri,bno),HttpStatus.OK);
	}
	
	@PreAuthorize("principal.username == #dto.replyer")
	@PutMapping(value="/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyDTO dto){
		log.info("댓글수정 "+dto);
		
		return service.update(dto)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{rno}") @PreAuthorize("principal.username == #dto.replyer")
	public ResponseEntity<String> delete(@PathVariable("rno") int rno,@RequestBody ReplyDTO dto){
		log.info("댓글삭제 "+rno);
		
		return service.delete(rno)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
