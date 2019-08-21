package com.kevin.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kevin.studentroster.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	   List<Contact> findAll();

	    void deleteById(Long id);

}