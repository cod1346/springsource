package com.spring.dependency;

public class TvMain {

	public static void main(String[] args) {
		
		//SonySpeaker speaker = new SonySpeaker();
		
		TV tv = new SamsungTv(new SonySpeaker());
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}

}
