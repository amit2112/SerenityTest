package com.studentapp.utils;

import java.util.Random;

public class TestUtils {
	
	public static String getRandmValue(){
		Random random = new Random();
		int randomInt = random.nextInt(10000);
		return Integer.toString(randomInt);
	}
}
