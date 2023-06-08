package com.spring.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class SpUserDTO {
	private String userId;
	private String email;
	private String password;
	private boolean enabled;
	
	private List<SpUserAuthorityDTO> authorites;
}
