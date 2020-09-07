package com.studentapp.junit.studentinfo;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import static org.junit.Assert.*;
	import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder
public class StudentCRUDTest extends TestBase {
	static String firstName = "UserFN" + TestUtils.getRandmValue();
	static String lastName = "UserLN" + TestUtils.getRandmValue();
	static String email = TestUtils.getRandmValue() + "FNLN@gmail.com";
	static String programme = "Computer Science";
	static int studentId;

	@Steps
	StudentSerenitySteps steps;
	
	@Title("This will create a user..")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("Java");
		courses.add("Appium");

		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
	}

	@Title("Verify if the user is added")
	@Test
	public void test002() {
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
		studentId = (int)value.get("id");
	}

	@Title("This will update the student")
	@Test
	public void test003() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("Java");
		courses.add("Appium");

		firstName = firstName + "_Updated";
		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
	
		//Verifying the updated student
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
	}
	
	
	@Title("This will delete the created student")
	@Test
	public void test004(){
		steps.deleteStudentByFirstName(studentId);
		steps.getStudentById(studentId).statusCode(404);
	}
}
