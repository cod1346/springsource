package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;
import com.spring.service.BookServiceImpl;

public class BookMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		BookService service = (BookService) ctx.getBean("bookService");
		
		BookDTO insertDto = new BookDTO(1099, "스프링", "스프링", 50000, "디스크립션");
		if(service.insertBook(insertDto)) {
			System.out.println("입력성공");
		}
		
//		BookDTO updateDto = new BookDTO();
//		updateDto.setCode(8888);
//		updateDto.setPrice(100000);
//		if(service.updateBook(updateDto)) {
//			System.out.println("수정성공");
//		}
//		
//		BookDTO row = service.getBook(8888);
//		System.out.println(row);
//		
//		if(service.deleteBook(0)) {
//			System.out.println("삭제성공");
//		}
		
		//BookService service = new BookServiceImpl(new BookDAO());
//		service.updateBook(1234, 8888);
//		List<BookDTO> list = service.getBookList();
//
//		
//		for (BookDTO bookDTO : list) {
//			System.out.println(bookDTO);
//		}
		
	}

}
