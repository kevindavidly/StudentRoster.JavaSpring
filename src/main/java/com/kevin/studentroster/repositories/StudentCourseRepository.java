package com.kevin.studentroster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.studentroster.models.StudentCourse;

@Repository

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Long> {

	void deleteById(Long id);

}