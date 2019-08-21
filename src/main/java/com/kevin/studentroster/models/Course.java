package com.kevin.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "courses")
public class Course {


	// ------------------------------------------------------------------------------------
	// Students Table
	// ------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String name;

	
	// ------------------------------------------------------------------------------------
	// many to many Relationships
	// ------------------------------------------------------------------------------------
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "courses_students",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> students;
	
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
	
	public Course() {
	}
	
	public Course(String name, List<Student> students) {
		this.name = name;
		this.students = students;
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
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Student> getStudents() {
		return students;
	}
	
	
	public void setStudents(List<Student> students) {
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