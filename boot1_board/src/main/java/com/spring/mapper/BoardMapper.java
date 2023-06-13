 package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
@Mapper
public interface BoardMapper {
	public List<BoardDTO> list(Criteria cri);
	public int insert(BoardDTO dto);
	public BoardDTO read(int bno);
	public int update(BoardDTO dto);
	public int delete(int bno);
	public int totalCnt(Criteria cri);
	public int updateReplyCnt(@Param("bno")int bno,@Param("amount")int amount);
	
	public BoardDTO readAttach(int bno);
}
