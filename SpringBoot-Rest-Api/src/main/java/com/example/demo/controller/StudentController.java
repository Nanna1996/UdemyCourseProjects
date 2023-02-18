package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

	@GetMapping("/get")
	public ResponseEntity<Student> addStudent() {
		Student student = new Student(101, "Harsha", 85d);
		// return new ResponseEntity<Student>(student, HttpStatus.OK);
		// return ResponseEntity.ok(student);
		return ResponseEntity.ok().header("custom-header", "Harsha").body(student);
	}

	// list of students
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> addStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(102, "Hari", 75d));
		students.add(new Student(103, "Ravi", 80d));
		students.add(new Student(104, "Arya", 70d));
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	// using path variable for all fields
	// http://localhost:2023/student/students/1/Harsha/90d

	@GetMapping("/students/{id}/{name}/{marks}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
			@PathVariable("name") String name, @PathVariable("marks") Double marks) {
		Student student = new Student(studentId, name, marks);
		return ResponseEntity.ok(student);
	}

	// Spring boot RestApi with request param

	// http://localhost:2023/student/students/query?id=201&name=Harsha&marks=97

	@GetMapping("/students/query")
	public ResponseEntity<Student> studentRequestParam(@RequestParam int id, @RequestParam String name,
			@RequestParam Double marks) {
		Student student = new Student(id, name, marks);
		return ResponseEntity.ok(student);
	}

	// Spring boot restapi that handles http post request
	// http://localhost:2023/student/create

	@PostMapping("/create")
	// @ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getName());
		System.out.println(student.getMarks());
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}

	// Spring Boot Rest-API that handles Http Put Request
	// http://localhost:2023/student/student/12/update

	@PutMapping("/student/{id}/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
		System.out.println(student.getName());
		System.out.println(student.getMarks());
		return ResponseEntity.ok(student);
	}

	// Spring Boot Rest-API that handles Http Delete Request
	// http://localhost:2023/student/student/101/delete

	@DeleteMapping("/student/{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		return ResponseEntity.ok("Student deleted Successfully!..: " + id);
	}

}
