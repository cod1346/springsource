package com.spring.schedule;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.domain.AttachFileDTO;
import com.spring.mapper.AttachMapper;

@Component
public class TaskTest {
	@Autowired
	private AttachMapper mapper;
//	<ul>
//	<li>second</li>
//	<li>minute</li>
//	<li>hour</li>
//	<li>day of month</li>
//	<li>month</li>
//	<li>day of week</li>
//	</ul>
	
	//@Scheduled(cron = "* 0 * * * *") 매 0분 매초마다(한시간마다) 스케줄링
	//@Scheduled(cron = "* * 0 * * *") 매 00시(자정)매분 매초마다(1일에한번) 스케줄링
	//@Scheduled(cron = "* * * 1 * *") 매월 1일 매초 매분 매시 마다 스케줄링
	@Scheduled(cron = "0 0 2 * * *")
	public void scheduleTest() {
		//어제날짜의 파일 목록 가져오기
		List<AttachFileDTO> oldList = mapper.oldFiles();
		
//		List<Path> pathList = new ArrayList<Path>();
//		
//		for (AttachFileDTO dto : oldList) {
//			Path path = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
//			pathList.add(path);
//			
//			if (dto.isFileType()) {
//				Path thumb = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
//				pathList.add(thumb);
//			}
//		}
		
		//oldList 를 stream 변환,
		//Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
		List<Path> pathList = 
				oldList.stream()
					.map(dto->Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName()))
					.collect(Collectors.toList());
		
		oldList.stream()
			.filter(dto->dto.isFileType())
			.map(dto->Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName()))
			.collect(Collectors.toList())
			.forEach(dto ->pathList.add(dto));
		
		
		System.out.println(pathList);
		
		String yesterday = getFolderYesterDay();
		
		File targetDir=Paths.get("c:\\upload\\",yesterday).toFile();
		System.out.println("targetDir : "+targetDir);
		
		File[] removeFiles=targetDir.listFiles(f->pathList.contains(f.toPath())==false);
		
		for(File remove:removeFiles) {
			remove.delete();
		}
	}
	
	private String getFolderYesterDay() {
		LocalDate yesterday = LocalDate.now();//.minusDays(1);
		
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return str.replace("-", File.separator);//운영체제에 맞는 폴더구분자로 변경 ex)\,/
	}
//	@Scheduled(fixedDelay = 10000) 10000밀리세컨드마다 (고정알림)
//	public void schedulerTest2() {
//		System.out.println("10초마다 스케쥴링...");
//	}
}
