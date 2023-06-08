package com.spring.basic;

public class TvMain {

	public static void main(String[] args) {
//		LgTv lgTV = new LgTv();
//		lgTV.turnOn();
//		lgTV.soundUp();
//		lgTV.soundDown();
//		lgTV.turnOff();
		TV samsungTV = new SamsungTv();
		samsungTV.powerOn();
		samsungTV.volumeUp();
		samsungTV.volumeDown();
		samsungTV.powerOff();
	}

}
