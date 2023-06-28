package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.Student;
import lv.venta.services.IStudentService;
import lv.venta.services.IThesisAplicationService;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/application")
public class ThesisApplicationController {

	@Autowired
	private IThesisAplicationService applicationService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private IThesisService thesisService;

	@PostMapping("/addNew/{thesisId}")
	public String addNewApplications(@Valid @ModelAttribute ThesisApplication application, BindingResult result, 
            @PathVariable("thesisId") long thesisId, Model model) throws Exception {
		try {
			Thesis thesis = thesisService.getThesisById(thesisId);
			if (thesis == null) {
				throw new Exception("Thesis not found with ID: " + thesisId);
			}
			application.setThesis(thesis);
			ArrayList<Student> students = (ArrayList<Student>) studentService.findAll();
			model.addAttribute("students", students);
			model.addAttribute("thesis", thesis);
			model.addAttribute("application", new ThesisApplication());
			return "application-add-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
}