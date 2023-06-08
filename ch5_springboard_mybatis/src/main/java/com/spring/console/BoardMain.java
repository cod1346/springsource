package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BoardDTO;
import com.spring.service.BoardService;

public class BoardMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		BoardService service = (BoardService) ctx.getBean("board");
		

		BoardDTO dto=service.getRow(2);
		System.out.println(dto);
		
		
	}

}
