package com.anand.springboot.service;

import java.util.List;


import com.anand.springboot.model.Student;

public interface StudentService {
	Student saveStudent(Student student);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	Student updateStudent(Student student, long id);
	void deleteStudent(long id);
	List<Student> findStudentsWithSorting(String field);

	
}
