package com.kevin.studentroster.controllers;

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

import com.kevin.studentroster.models.Course;
import com.kevin.studentroster.services.CourseService;
import com.kevin.studentroster.services.StudentService;

@Controller
@RequestMapping("/courses")

public class CourseController {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

//----------------------------------------------------------------
//render student create page
//----------------------------------------------------------------

	@GetMapping("/new")
	public String newClass(@ModelAttribute("course") Course course) {
		return "/courses/new.jsp";
	}

//----------------------------------------------------------------
//process create
//----------------------------------------------------------------

	@PostMapping("/new/process")
	public String create(@Valid @ModelAttribute("course") Course course, BindingResult result) {
		if (result.hasErrors()) {
			return "/courses/new";
		} else {
			courseService.createCourse(course);
			return "redirect:/students";
		}
	}

//----------------------------------------------------------------
//find one by id
//----------------------------------------------------------------

	@GetMapping("/{id}")
	public String showAllStudents(Model model, @PathVariable("id") Long course_id) {
		Course course = courseService.findById(course_id);
		model.addAttribute("course", course);
		return "/courses/show.jsp";
	}
}