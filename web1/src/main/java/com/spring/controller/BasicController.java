package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller @RequestMapping("/sample") @Slf4j
public class BasicController {
		
	@RequestMapping("/basic")
	public void basic() {
		log.info("basic......요청");
	}
}
