package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;
import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("Creating student with firstName : {0}, lastName : {1}, email : {2}, programme : {3}, courses : {4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
			List<String> courses){
		
		StudentClass student = new StudentClass();
		student.setfirstName(firstName);
		student.setlastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourse(courses);

		return SerenityRest.given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
	}
	
	@Step("Getting the student information with the firstName : {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName){
		return SerenityRest.rest().given()
			.when()
			.get("/list")
			.then()
			.log()
			.all()
			.statusCode(200)
			.extract()
			.path("findAll{it.firstName=='" + firstName + "'}.get(0)");
	}
	
	@Step("Updating student with studentId : {0}, firstName : {1}, lastName : {2}, email : {3}, programme : {4}, courses : {5}")
	public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme,
			List<String> courses){
		
		StudentClass student = new StudentClass();
		student.setfirstName(firstName);
		student.setlastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourse(courses);


		return SerenityRest.given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.log()
		.all()
		.when()
		.body(student)
		.put("/" +studentId)
		.then()
		.log()
		.all();
	}
	
	@Step("Deleting the student information with the studentId : {0}")
	public void deleteStudentByFirstName(int studentId){
		SerenityRest.rest()
		.given()
		.when()
		.delete("/"+studentId);
	}
	
	@Step("Getting the student with studentId : {0}")
	public ValidatableResponse getStudentById(int studentId){
		return SerenityRest.rest()
				.given()
				.when()
				.get("/"+studentId)
				.then();
	}
}