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

import com.kevin.studentroster.models.Course;
import com.kevin.studentroster.models.Student;
import com.kevin.studentroster.models.StudentCourse;
import com.kevin.studentroster.services.ContactService;
import com.kevin.studentroster.services.CourseService;
import com.kevin.studentroster.services.DormService;
import com.kevin.studentroster.services.StudentCourseService;
import com.kevin.studentroster.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	ContactService contactService;

	@Autowired
	CourseService courseService;

	@Autowired
	DormService dormService;

	@Autowired
	StudentCourseService studentCourseService;

//----------------------------------------------------------------
//find all
//----------------------------------------------------------------

	@GetMapping("")
	public String index(Model model) {
		List<Student> students = studentService.allStudents();
		model.addAttribute("students", students);
		return "/students/index.jsp";
	}

//----------------------------------------------------------------
//render student create page
//----------------------------------------------------------------

	@GetMapping("/new")
	public String newStudent(@ModelAttribute("student") Student student) {
		return "/students/new.jsp";
	}

//----------------------------------------------------------------
//process create
//----------------------------------------------------------------

	@PostMapping("/new/process")
	public String create(@Valid @ModelAttribute("student") Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "/students/new";
		} else {
			studentService.createStudent(student);
			return "redirect:/students";
		}
	}

//----------------------------------------------------------------
//render add students to course page 
//----------------------------------------------------------------

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Long student_id) {

		model.addAttribute("middleTable", new StudentCourse());
		
		List<Course> courses = courseService.findAll();
		model.addAttribute("courses", courses);

		Student student = studentService.findById(student_id);
		model.addAttribute("student", student);

		return "/students/show.jsp";
	}

//----------------------------------------------------------------
//process add students to course page 
//----------------------------------------------------------------
	
	@PostMapping("/{id}/process")
	public String addClass(Model model, @PathVariable("id") Long student_id, @RequestParam("course_id") Long course_id) {
		Student student = studentService.findById(student_id);
		Course course = courseService.findById(course_id);
		StudentCourse sc = new StudentCourse (student, course);
		studentCourseService.createRelationship(sc);
		System.out.println("controller");
		return "redirect:/students/" + student_id;
	}
	
//----------------------------------------------------------------
// Delete class from student
//----------------------------------------------------------------	
	
	@GetMapping("/{id}/delete")
	public String removeStudent(@PathVariable("id") Long course_id, @RequestParam("student_id")Long student_id) {
		studentService.removeCourse(student_id, course_id);
		return "redirect:/students/" + student_id; 
	}

}