package com.spring.dependency;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class SamsungTV implements TV {
	
	//@Autowired
	@Inject
	@Qualifier("sonySpeaker")
	private Speaker speaker;
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 파워 On");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 파워 Off");
	}
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
	
	public SamsungTV() {
		System.out.println("SamsungTV 인스턴스 생성 - default 생성자");
	}
	
	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker;
		System.out.println("SamsungTV 인스턴스 생성 - 인자 생성자");
	}
	
}
