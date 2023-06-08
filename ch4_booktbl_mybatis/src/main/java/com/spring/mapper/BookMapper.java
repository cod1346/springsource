package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BookDTO;

public interface BookMapper {
	public int insert(BookDTO dto);
	public int update(@Param("price") int price,@Param("code")int code);
	public int delete(int code);
	public BookDTO getRow(int code);
	public List<BookDTO> getRows();
}
