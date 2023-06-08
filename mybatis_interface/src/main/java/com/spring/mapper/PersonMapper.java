package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.spring.domain.PersonDTO;

public interface PersonMapper {
	
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insert(PersonDTO insert);
	
	
	public int insertPerson(PersonDTO insert);
	public int updatePerson(PersonDTO insert);
	public int deletePerson(String id);
	public PersonDTO selectOne(String id);
	public List<PersonDTO> selectAll();
}
