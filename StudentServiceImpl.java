package com.anand.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anand.springboot.exception.ResourceNotFoundException;
import com.anand.springboot.model.Student;
import com.anand.springboot.repository.StudentRepository;
import com.anand.springboot.service.StudentService;

import org.springframework.data.domain.Sort;
@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
//		Optional<Student> student = studentRepository.findById(id);
//		if(student.isPresent()) {
//			return student.get();
//		}else {
//			throw new ResourceNotFoundException("Student", "Id", id);
//		}
		return studentRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Student", "Id", id));
		
	}

	@Override
	public Student updateStudent(Student student, long id) {
		
		// we need to check whether student with given id is exist in DB or not
		Student existingStudent = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", id)); 
		
		existingStudent.setStudentName(student.getStudentName());
		existingStudent.setDob(student.getDob());
		// save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}

	@Override
	public void deleteStudent(long id) {
		
		// check whether a student exist in a DB or not
		studentRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Student", "Id", id));
		studentRepository.deleteById(id);
	}

	//sorting data according to Date Of Birth

//	@Override
//	public List<Student> getAllStudentsSortedByDobAsc() {
//	    //Sort sort = Sort.by(Sort.Order.asc("dob"));
//	    //Sort sortBy = Sort.by(Sort.Direction.ASC, "dob");
//	    Sort sortBy = Sort.by(new Sort.Order(Sort.Direction.ASC, "dob").ignoreCase());
//
//	    return studentRepository.findAll(sortBy);
//	}

	@Override
	public List<Student> findStudentsWithSorting(String field) {
	    Sort sort = Sort.by(Sort.Order.asc(field));
	    return studentRepository.findAll(sort);
	    

	}

	
	
}
