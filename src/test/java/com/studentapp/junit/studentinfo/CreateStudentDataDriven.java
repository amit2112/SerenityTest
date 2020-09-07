package com.studentapp.junit.studentinfo;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="5x")
@UseTestDataFrom("testdata/StudentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDriven extends TestBase{
	private String firstName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getCourses() {
		return course;
	}
	public void setCourses(String courses) {
		this.course = course;
	}
	public StudentSerenitySteps getSteps() {
		return steps;
	}
	public void setSteps(StudentSerenitySteps steps) {
		this.steps = steps;
	}
	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	
	@Steps
	StudentSerenitySteps steps;
	@Title("Test to add multiple students")
	@Test
	public void test001(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add(course);
		steps.createStudent(firstName, lastName, email, programme, courses).spec(ReusableSpecifications.getGenericResponseSpec());
	}
}
