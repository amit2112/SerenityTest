package com.studentapp.junit.studentinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	@Test
	public void getAllStudent(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	@Title("This test is forced to be failed :) ")
	@Test
	public void failThis(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void pendingTest(){
		
	}
	
	@Ignore
	@Test
	public void skippedTest(){
		
	}
	
	@Test
	public void errorTest(){
		System.out.println("This is an error test : "+(5/0));
	}
	
	@Test
	public void compromiseTest() throws FileNotFoundException{
		File f = new File("E://file.txt");
		FileReader fr = new FileReader(f);
	}
	
	@Manual
	@Test
	public void manualTest() {

	}
}
