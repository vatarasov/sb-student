package ru.vtarasov.sb.student;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.NONE, classes = { StudentRegistrationServiceImpl.class, StudentRepositoryImpl.class })
public class StudentRegistrationServiceTest {

	@Autowired
	private StudentRegistrationService studentRegistrationService;

	@Test
	public void shouldRegisterFindUnregisterAndNotFindStudent() {
		Student registeredStudent = studentRegistrationService.register(Student.of("Student", 16));
		Student foundedStudent = studentRegistrationService.find(registeredStudent.getId()).get();
		Assert.assertThat(foundedStudent, Matchers.equalTo(registeredStudent));

		studentRegistrationService.unregister(registeredStudent);
		Student notFoundedStudent = studentRegistrationService.find(registeredStudent.getId()).orElse(null);
		Assert.assertThat(notFoundedStudent, Matchers.nullValue());
	}
}
