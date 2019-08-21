package com.kevin.studentroster.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "contacts")

public class Contact {

	// ------------------------------------------------------------------------------------
	// Contacts Table
	// ------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String address;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String city;

	@Size(min = 2, max = 20, message = "Requires at least 2 characters")
	private String state;

	// ------------------------------------------------------------------------------------
	// 1 to 1 Relationships
	// ------------------------------------------------------------------------------------

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

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

	public Contact() {
	}

	public Contact(String address, String city, String state, Student student) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.student = student;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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