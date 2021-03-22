package com.techgenizer.springSecurity.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

	private static List<Student> studentList = Arrays.asList(new Student(1, "Joe"), new Student(2, "Doe"),
			new Student(3, "Dan"));

	@GetMapping
	public List<Student> getAllStudentList() {
		return studentList;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("registerNewStudent");
	}
	
	@DeleteMapping(path ="{studentId}")
	public void deleteStudent(@PathVariable Integer studentId) {
		System.out.println("deleteStudent");
	}

	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable Integer studentId,@RequestBody Student student) {
		System.out.println("updateStudent");
	}
	
}
