package com.spring.dependency;

import org.springframework.stereotype.Component;

@Component
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("AppleSpeaker 인스턴스 생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker Volume Up");

	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker Volume Down");

	}

}
