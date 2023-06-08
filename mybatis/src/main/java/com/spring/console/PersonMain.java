package com.spring.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.PersonDTO;
import com.spring.service.PersonService;

public class PersonMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService) ctx.getBean("person");
		
		//PersonDTO insertPerson = new PersonDTO("hong123", "홍길동");
		//System.out.println(service.insertPerson(insertPerson)?"성공":"실패");
		
		//System.out.println(service.getRow("kim123"));
		
		System.out.println(service.updatePerson(new PersonDTO("kang123","강동주"))?"수정성공":"수정실패");
		System.out.println(service.deletePerson("kim123")?"삭제성공":"삭제실패");
		System.out.println(service.getRows());
	}

}
