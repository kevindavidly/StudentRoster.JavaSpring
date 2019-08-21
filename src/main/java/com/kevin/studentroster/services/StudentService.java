package com.kevin.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.studentroster.models.Course;
import com.kevin.studentroster.models.Student;
import com.kevin.studentroster.repositories.CourseRepository;
import com.kevin.studentroster.repositories.StudentCourseRepository;
import com.kevin.studentroster.repositories.StudentRepository;

@Service

public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentCourseRepository studentCourseRepository;
	
	@Autowired
	CourseService courseService;
	
	
//  ----------------------------------------------------------------
//  find all
//  ----------------------------------------------------------------

	public List<Student> allStudents() {
		return studentRepository.findAll();
	}
	
	public List<Student> findByDormIsNull(){
		return studentRepository.findByDormIsNull();
	}
	

//  ----------------------------------------------------------------
//  create 
//  ----------------------------------------------------------------
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	
//  ----------------------------------------------------------------
//  Find one by id
//  ----------------------------------------------------------------
	public Student findById(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
	
//  ----------------------------------------------------------------
//  delete
//  ----------------------------------------------------------------
	
	public Student removeCourse( Long student_id, Long course_id) {
		Student oneStudent = findById(student_id);
		Course oneCourse = courseService.findById(course_id);
		
		List <Course> studentClasses = oneStudent.getCourses();
		System.out.println(oneStudent.getCourses()); // gets dictionary of objects
		
		studentClasses.remove(oneCourse); //remove the one course
		
		createStudent(oneStudent);
		return null;
	}

}