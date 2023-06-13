package com.spring.service;

import java.util.List;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;

public interface BoardService {
	public List<BoardDTO> getList(Criteria cri);
	public BoardDTO getRow(int bno);
	public boolean insert(BoardDTO dto);
	public boolean update(BoardDTO dto);
	public boolean delete(int bno);
	public int getTotalCnt(Criteria cri);
	public List<AttachFileDTO> getAttachList(int bno);
}
