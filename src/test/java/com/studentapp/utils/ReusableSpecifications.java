package com.studentapp.utils;

import java.util.concurrent.TimeUnit;

import org.mockito.internal.matchers.LessThan;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpec;
	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responseSpec;
	
	public static RequestSpecification getGenericRequestSpec(){
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestSpec = rspec.build();
		
		return requestSpec;
	}
	
	
	public static ResponseSpecification getGenericResponseSpec(){
		respec = new ResponseSpecBuilder();
		respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respec.expectHeader("Transfer-Encoding", "chunked");
		respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responseSpec = respec.build();
		return responseSpec;
	}
}
