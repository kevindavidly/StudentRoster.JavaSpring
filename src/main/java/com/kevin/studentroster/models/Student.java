package com.kevin.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students")
public class Student {

	// ------------------------------------------------------------------------------------
	// Students Table
	// ------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String first_name;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String last_name;
	
	@NotNull
	private Integer age;

	// ------------------------------------------------------------------------------------
	// 1-1 Relationships
	// ------------------------------------------------------------------------------------
	
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	
	private Contact contact;
	
	
	// ------------------------------------------------------------------------------------
	// many to 1 Relationships
	// ------------------------------------------------------------------------------------
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dorm_id")
	private Dorm dorm;
	
	
	// ------------------------------------------------------------------------------------
	// many to many Relationships
	// ------------------------------------------------------------------------------------

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "students_courses",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
			)
	private List<Course> courses;
	
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
	

	
	public Student() {
	}
	
	public Student(String first_name, String last_name, Integer age) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
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
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Dorm getDorm() {
		return dorm;
	}
	
	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
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