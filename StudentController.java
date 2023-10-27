package com.anand.springboot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.anand.springboot.model.Student;
import com.anand.springboot.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	// build create student REST API
	@PostMapping()
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	
	// build get all student REST API
	@GetMapping
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	// build get student by id REST API
	// http://localhost:8080/api/students/1001
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId){
		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	
	// build update student REST API
	// http://localhost:8080/api/students/1007
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id
												  ,@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}
	
	// build delete student REST API
	// http://localhost:8080/api/students/1007
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
		
		// delete student from DB
		studentService.deleteStudent(id);
		
		return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
	}
	@GetMapping("/sort/{field}")
	public ResponseEntity<List<Student>> getStudentsSortedByField(@PathVariable("field") String field) {
	    List<Student> sortedStudents = studentService.findStudentsWithSorting(field);
	    return new ResponseEntity<>(sortedStudents, HttpStatus.OK);
	}

	
}
