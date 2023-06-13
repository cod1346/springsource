package com.example.restaurant.naver.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SearchImageRes {
	
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<SearchImageItem> items;
}
