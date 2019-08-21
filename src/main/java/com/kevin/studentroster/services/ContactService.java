package com.kevin.studentroster.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.studentroster.models.Contact;
import com.kevin.studentroster.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;
	
//  ----------------------------------------------------------------
//  find all
//  ----------------------------------------------------------------
	
	public List<Contact> allContacts() {
		return contactRepository.findAll();
	}
	
//  ----------------------------------------------------------------
//  create 
//  ----------------------------------------------------------------
	
	public Contact createContact(Contact contact) {
		return contactRepository.save(contact);
	}
}