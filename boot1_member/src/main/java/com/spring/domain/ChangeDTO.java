package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ChangeDTO {
	private String userId;
	private String currentpassword;
	private String newpassword;
	private String confirmpassword;
	
	public boolean passwordEquals() {
		return newpassword.equals(confirmpassword);
	}
}
