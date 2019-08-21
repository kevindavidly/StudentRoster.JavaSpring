package com.kevin.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.studentroster.models.Course;
import com.kevin.studentroster.repositories.CourseRepository;


@Service

public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	
//  ----------------------------------------------------------------
//  find all
//  ----------------------------------------------------------------

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	

//  ----------------------------------------------------------------
//  create 
//  ----------------------------------------------------------------
	
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	
	// Find one by id
	public Course findById(Long id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isPresent()) {
			return optionalCourse.get();
		} else {
			return null;
		}
	}

	

}