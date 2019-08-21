package com.kevin.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.studentroster.models.Student;

@Repository

public interface StudentRepository extends CrudRepository <Student, Long>{
	
	   List<Student> findAll();

	   void deleteById(Long id);
	    
	   List<Student> findByDormIsNull();

}