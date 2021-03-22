package com.techgenizer.springSecurity.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private static List<Student> studentList = Arrays.asList(new Student(1, "Joe"), new Student(2, "Doe"),
			new Student(3, "Dan"));

	@GetMapping(path = "{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return studentList.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist."));

	}
}
