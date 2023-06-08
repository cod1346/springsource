package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AttachFileDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Slf4j
public class uploadAjaxController {

	@GetMapping("/uploadAjax")
	public void uploadAjaxGet() {
		log.info("uploadAjaxGet");
	}

//	@PostMapping("/uploadAjax")
//	public ResponseEntity<List<String>> uploadAjaxPost(MultipartFile[] uploadFile) {
//		log.info("upload요청");
//		
//		List<String> fileList = new ArrayList<String>();
//		for (MultipartFile multipartFile : uploadFile) {
//			
//			System.out.println("-------------------------------------------");
//			log.info("file name : "+multipartFile.getOriginalFilename());
//			log.info("file size : "+multipartFile.getSize());
//			System.out.println("-------------------------------------------");
//			
//			String uploadPath = "c:\\upload";
//			UUID uuid = UUID.randomUUID();
//			String fileName = uuid.toString()+"_"+multipartFile.getOriginalFilename();
//			
//			fileList.add(multipartFile.getOriginalFilename());
//			try {
//				multipartFile.transferTo(new File(uploadPath, fileName));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return new ResponseEntity<>(fileList,HttpStatus.OK);
//	}

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("upload요청");

		List<AttachFileDTO> fileList = new ArrayList<AttachFileDTO>();

		String uploadPath = "c:\\upload";
		String uploadFolderPath = getFolder();
		File uploadFullPath = new File(uploadPath, uploadFolderPath);
		System.out.println(uploadFullPath);
		if (!uploadFullPath.exists()) {
			uploadFullPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {

			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			File saveFile = new File(uploadFullPath, fileName);

			AttachFileDTO attach = new AttachFileDTO();
			attach.setFileName(multipartFile.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			try {
				multipartFile.transferTo(saveFile);
				if (checkImageType(saveFile)) {
					attach.setFileType(true);

					BufferedImage origin = ImageIO.read(saveFile);

					File thumbnail = new File(uploadFullPath, "s_" + fileName);

					double ratio = 10;
					int width = (int) (origin.getWidth() / ratio);
					int height = (int) (origin.getHeight() / ratio);
					Thumbnails.of(origin).size(width, height).toFile(thumbnail);
					System.out.println("------------------------------------");
					System.out.println(attach.isFileType());
					System.out.println("------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(attach);
		}
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}

	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			System.out.println(contentType);
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("파일 요청 " + fileName);

		File file = new File("c:\\upload\\" + fileName);

		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			headers.add("content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName, @RequestHeader("User-Agent") String userAgent) {
		log.info("파일 다운로드 요청" + fileName);

		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);

		// uuid를 포함한 파일명
		String oriFileName = resource.getFilename();
		// uuid를 제거한 파일명
		String splitUuid = oriFileName.substring(oriFileName.indexOf("_") + 1);

		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		String downloadName = null;
		try {
			if (userAgent.contains("Trident") || userAgent.contains("Edge")) {
				// uuid를 포함한 파일명
				// downloadName =
				// URLEncoder.encode(resource.getFilename(),"utf-8").replaceAll("\\+", "");

				downloadName = URLEncoder.encode(splitUuid, "utf-8").replaceAll("\\+", "");
			} else {
				// uuid를 포함한 파일명
				//downloadName = new String(resource.getFilename().getBytes("utf-8"), "ISO-8859-1");
				downloadName = new String(splitUuid.getBytes("utf-8"), "ISO-8859-1");
			}

			headers.add("Content-Disposition", "attachment;filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	

	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName,String type) {
		log.info("파일 제거 요청 "+fileName+",type "+type);
		
		try {
			File file = new File("c:\\upload\\", URLDecoder.decode(fileName,"utf-8"));
			
			file.delete();

			if(type.equals("image")) {
				String largeName = file.getAbsolutePath().replace("s_","");
				file = new File(largeName);
				
				file.delete();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

}
