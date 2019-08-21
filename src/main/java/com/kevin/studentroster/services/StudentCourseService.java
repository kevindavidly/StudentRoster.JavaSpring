package com.kevin.studentroster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.studentroster.models.StudentCourse;
import com.kevin.studentroster.repositories.StudentCourseRepository;

@Service

public class StudentCourseService {
	@Autowired
	StudentCourseRepository studentCourseRepository;
	

	public StudentCourse createRelationship(StudentCourse studentCourse) {
		return studentCourseRepository.save(studentCourse);
	}	
	
	// delete
	public void deleteRelationship(Long id) {
		studentCourseRepository.deleteById(id);
		return;
	}

}