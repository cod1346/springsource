package com.spring.dependency;

public class SamsungTv implements TV {
	
	private SonySpeaker speaker;
	public SonySpeaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(SonySpeaker speaker) {
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
	
	public SamsungTv() {
		
	}
	
	public SamsungTv(SonySpeaker speaker) {
		super();
		this.speaker = speaker;
	}
}
