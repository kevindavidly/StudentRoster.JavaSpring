package com.kevin.studentroster.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.studentroster.models.Dorm;
import com.kevin.studentroster.models.Student;
import com.kevin.studentroster.services.DormService;
import com.kevin.studentroster.services.StudentService;

@Controller
@RequestMapping("/dorms")

public class DormController {

	@Autowired
	DormService dormService;

	@Autowired
	StudentService studentService;

	// ----------------------------------------------------------------
	// find all dorms
	// ----------------------------------------------------------------

	@GetMapping("")
	public String index(Model model) {
		List<Dorm> dorms = dormService.allDorms();
		model.addAttribute("dorms", dorms);
		return "/dorms/index.jsp";
	}

	// ----------------------------------------------------------------
	// render dorm create page
	// ----------------------------------------------------------------

	@GetMapping("/new")
	public String newDorm(@ModelAttribute("dorm") Dorm dorm) {
		return "/dorms/new.jsp";
	}

	// ----------------------------------------------------------------
	// process create dorm
	// ----------------------------------------------------------------

	@PostMapping("/new/process")
	public String create(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		if (result.hasErrors()) {
			return "/dorms/new";
		} else {
			dormService.createDorm(dorm);
			return "redirect:/dorms";
		}
	}
	
//  ----------------------------------------------------------------
//  render add students to dorm page 
//  ----------------------------------------------------------------
	@GetMapping("/{id}")
	public String addStudent(Model model, @PathVariable("id") Long dorm_id, @ModelAttribute("student") Student student) {
		
		List<Student> students = studentService.findByDormIsNull();
		model.addAttribute("students", students);
		
		Dorm oneDorm = dormService.findById(dorm_id);
		model.addAttribute("dorm", oneDorm);

		return "/dorms/addStudent.jsp";
	}
	
//  ----------------------------------------------------------------
//  POST add students to dorm page 
//  ----------------------------------------------------------------
	@PostMapping("/{id}/process")
	public String addStudentProcess(Model model, @RequestParam("students") Long student_id, @PathVariable("id") Long dorm_id, @ModelAttribute("newDorm") Dorm dorm) {
		Dorm oneDorm = dormService.findById(dorm_id);
		Student oneStudent = studentService.findById(student_id);
		oneStudent.setDorm(oneDorm);
		studentService.createStudent(oneStudent);
		return "redirect:/dorms/" + dorm_id;
	}

	
//  ----------------------------------------------------------------
//  Remove student from Dorm 
//  ----------------------------------------------------------------
	@GetMapping("/{id}/delete")
	public String removeStudent(@PathVariable("id") Long student_id) {
		
		Student oneStudent = studentService.findById(student_id);
		Dorm oneDorm = oneStudent.getDorm();
		Long oneDorm_id = oneDorm.getId();
		oneStudent.setDorm(null);
		studentService.createStudent(oneStudent);
		
		return "redirect:/dorms/" + oneDorm_id; 
	}
	
	
	
	
}