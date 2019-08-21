package com.kevin.studentroster.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students_courses")

public class StudentCourse {

	// ------------------------------------------------------------------------------------
	// StudentCourse Table (HANDLES THE MANY TO MANY FOR CLASS AND STUDENTS)
	// ------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ------------------------------------------------------------------------------------
	// Student Class one to many Relationships
	// ------------------------------------------------------------------------------------
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")			
	private Student students;					// make singular for next time for easier query

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course courses;						// make singular for next time for easier query


	// ------------------------------------------------------------------------------------
	// createdAt and updatedAt
	// ------------------------------------------------------------------------------------

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	// ------------------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------------------

	public StudentCourse() {
	}

	public StudentCourse(Student students, Course courses) {
		this.students = students;
		this.courses = courses;
	}
	
	// ------------------------------------------------------------------------------------
	// Getters and Setters
	// ------------------------------------------------------------------------------------


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	public Student getStudents() {
		return students;
	}

	public void setStudents(Student students) {
		this.students = students;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}