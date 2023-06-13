package com.example.restaurant.naver.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SearchLocalReq {
	
	
	private String query = "";
	private String display = "1";
	private String start = "1";
	private String sort = "random";
	
	public MultiValueMap<String, String> toMultiValueMap(){
		
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		map.add("query", query);
		map.add("display", display);
		map.add("start", start);
		map.add("sort", sort);
		return map;
	}
}
