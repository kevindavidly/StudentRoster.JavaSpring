package com.kevin.studentroster.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevin.studentroster.models.Contact;
import com.kevin.studentroster.models.Student;
import com.kevin.studentroster.services.ContactService;
import com.kevin.studentroster.services.StudentService;

@Controller
@RequestMapping("/contacts")

public class ContactController {
	
	@Autowired
	ContactService contactService;
	@Autowired
	StudentService studentService;
	
	
	//----------------------------------------------------------------
	//render contact create page
	//----------------------------------------------------------------
	
	@GetMapping("/new")
	public String index(Model model, @ModelAttribute("contact") Contact contact) {
		List<Student> students = studentService.allStudents();
		model.addAttribute("students", students);
		return "/contacts/new.jsp";
	}
	

	//----------------------------------------------------------------
	//process create
	//----------------------------------------------------------------

		@PostMapping("/new/process")
		public String create(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
			if (result.hasErrors()) {
				return "/contacts/new";
			} else {
				contactService.createContact(contact);
				return "redirect:/students";
			}
		}

}