package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public int insert(MemberDTO dto);
	public int delete(@Param("userId") String userId,@Param("password") String password);
	public int update(MemberDTO dto);
	public MemberDTO getRow(@Param("userId") String userId,@Param("password") String password);
	public List<MemberDTO> getRows();
}
