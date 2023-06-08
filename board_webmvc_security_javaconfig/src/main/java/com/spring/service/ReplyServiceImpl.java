package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper mapper2;
	@Override
	public ReplyDTO read(int rno) {
		return mapper.read(rno);
	}

	@Override @Transactional
	public boolean insert(ReplyDTO dto) {
		
		mapper2.updateReplyCnt(dto.getBno(), 1);
		return mapper.insert(dto)==1?true:false;
	}

	@Override
	public ReplyPageDTO getList(Criteria cri,int bno) {
		List<ReplyDTO>list=mapper.listAll(cri,bno);
		int replyCnt = mapper.getCountByBno(bno);
		
		return new ReplyPageDTO(replyCnt, list);
	}

	@Override
	public boolean update(ReplyDTO dto) {
		return mapper.update(dto)==1?true:false;
	}

	@Override @Transactional
	public boolean delete(int rno) {
		
		ReplyDTO dto = mapper.read(rno);
		mapper2.updateReplyCnt(dto.getBno(), -1);
		return mapper.delete(rno)==1?true:false;
	}
	
	
}
