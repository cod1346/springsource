package com.spring.service;

import java.util.List;

import com.spring.domain.MemberDTO;

public interface MemberService {
	public boolean insertMember(MemberDTO dto);
	public boolean updateMember(MemberDTO dto);
	public boolean deleteMember(String userId,String password);
	public MemberDTO getRow(String userId,String password);
	public List<MemberDTO> gerRows();
}
