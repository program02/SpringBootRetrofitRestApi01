package com.exam;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("v1/")
public class StudentRestController {
	StudentRestApi studentRestApi;
	List<Student> stList;
	Student student;
	@Autowired
	public StudentRestController(StudentRestApi studentRestApi) {
		super();
		this.studentRestApi = studentRestApi;
	}
	@GetMapping("students")
	public List<Student> findStudents() {
		stList = new ArrayList<>();
		stList = studentRestApi.findAll();
		return stList;
	}
	@GetMapping("student/{id}")
	public Student findStudent(@PathVariable Integer id) {
		student = new Student();
		student = studentRestApi.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		return student;
	}
	@PostMapping("student")
	public Student saveStudent(Student s) {
		student = new Student();
		student = studentRestApi.save(s);
		return student;
	}
	
//	@PutMapping("student/{id}")
//	public Student updateputStudent(@PathVariable("id") int id, @Valid Student student) {		
//		student = studentRestApi.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));			
//		student = studentRestApi.save(student);
//		return student;
//	}
//	
	
	@PostMapping("student/{id}")
	public Student updateStudent(@PathVariable("id") int id, Student s) {		
		student = studentRestApi.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));			
		student.setName(s.getName());
		student.setEmail(s.getEmail());
		student.setPhone(s.getPhone());
		student = studentRestApi.save(student);
		return student;
	}
	
	@DeleteMapping("student/{id}")
	public Student deleteStudent(@PathVariable Integer id) {
		student = new Student();
		student = studentRestApi.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));			
		studentRestApi.deleteById(id);
		return student;
	}
}
