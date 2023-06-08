package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public BoardDTO getRow(int bno) {
		
//		BoardDTO dto = mapper.readAttach(bno);
//		log.info("상세 + 파일첨부 : "+dto);
		
		
		return mapper.read(bno); 
	}

	@Override @Transactional
	public boolean insert(BoardDTO dto) {
		boolean insertFlag = mapper.insert(dto)==1?true:false;
		
		if(dto.getAttachList()==null||dto.getAttachList().size()==0){
			return insertFlag;
		}
		
		dto.getAttachList().forEach(attach->{
			attach.setBno(dto.getBno());
			attachMapper.insert(attach);
		});
		return insertFlag;
	}

	@Override @Transactional
	public boolean update(BoardDTO dto) {
		boolean updateFlag = mapper.update(dto)==1?true:false;
		
		attachMapper.deleteAll(dto.getBno());
		
		if(dto.getAttachList()==null||dto.getAttachList().size()==0) {
			return updateFlag; 
		}
		
		dto.getAttachList().forEach(attach->{
			attach.setBno(dto.getBno());
			attachMapper.insert(attach); 
		});
		return updateFlag;
	}

	@Transactional
	@Override
	public boolean delete(int bno) {
		
		//자식 댓글 삭제
		replyMapper.deleteAll(bno);
		
		//첨부파일 삭제
		attachMapper.deleteAll(bno);	
		
		return mapper.delete(bno)==1?true:false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> getAttachList(int bno) {
		return attachMapper.select(bno);
	}

}
