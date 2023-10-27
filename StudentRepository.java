package com.anand.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.springboot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
