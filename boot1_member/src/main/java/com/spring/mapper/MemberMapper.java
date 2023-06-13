package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public String getPass(String userId);
	public AuthDTO login(String userId);
	public int insert(MemberDTO memberDTO);
	public int dupId(String userId);
	public int leave(String userId);
	public int update(ChangeDTO changeDTO);
}
