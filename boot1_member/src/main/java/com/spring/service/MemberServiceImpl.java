package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	@Autowired
	private BCryptPasswordEncoder bPasswordEncoder;
	
	@Override
	public AuthDTO login(LoginDTO loginDTO) {
		String encodePass = mapper.getPass(loginDTO.getUserId());
		
		if(bPasswordEncoder.matches(loginDTO.getPassword(), encodePass)) {
			return mapper.login(loginDTO.getUserId());
		}else {
			return null;
		}
		
	}

	@Override
	public boolean register(MemberDTO memberDTO) {
		memberDTO.setPassword(bPasswordEncoder.encode(memberDTO.getPassword()));
		return mapper.insert(memberDTO)==1?true:false;
	}

	@Override
	public boolean dupId(String userId) {
		return mapper.dupId(userId)>0?false:true;
	}

	@Override
	public boolean leave(LoginDTO loginDTO) {
		String encodePass = mapper.getPass(loginDTO.getUserId());
		
		if(bPasswordEncoder.matches(loginDTO.getPassword(), encodePass)) {
			return mapper.leave(loginDTO.getUserId())==1?true:false;
		}else {
			return false;
		}
	}

	@Override
	public boolean update(ChangeDTO changeDTO) {
		String encodePass = mapper.getPass(changeDTO.getUserId());
		
		if(bPasswordEncoder.matches(changeDTO.getCurrentpassword(), encodePass)) {
			changeDTO.setNewpassword(bPasswordEncoder.encode(changeDTO.getNewpassword()));
			return mapper.update(changeDTO)==1?true:false;
		}else {
			return false;
		}
	}

}
