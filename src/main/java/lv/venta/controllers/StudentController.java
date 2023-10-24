package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.Thesis;
import lv.venta.models.ThesisApplication;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;
import lv.venta.services.IAcademicPersonelService;
import lv.venta.services.IStudentService;
import lv.venta.services.IThesisAplicationService;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IAcademicPersonelService academicService;

	@Autowired
	private IThesisAplicationService applicationService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private IThesisService thesisService;

	private ArrayList<Area> areas = new ArrayList<>(Arrays.asList(Area.values()));
	private ArrayList<Complexity> complexities = new ArrayList<>(Arrays.asList(Complexity.values()));

	Logger logger = LoggerFactory.getLogger(ProfessorController.class);

	@GetMapping("/apply")
	public String showAwailableThesis(Model model) throws Exception {
		logger.debug("Method: showAwailableThesis");
		try {
			ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicService.findAll();
			ArrayList<Thesis> thesis = thesisService.selectAllByAssignedStudentIsNull();
			model.addAttribute("searchedElement", "Application");
			model.addAttribute("thesis", thesis);
			model.addAttribute("supervisors", supervisors);
			model.addAttribute("complexities", complexities);
			return "thesis-all-apply-page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/apply/{id}")
	public String showThesisInformation(@PathVariable("id") long id, Model model) {
		logger.debug("Method: showThesisInformation");
		try {
			Thesis thesis = thesisService.getThesisById(id);
			if (thesis == null) {
				throw new Exception("Thesis not found with ID: " + id);
			}
			model.addAttribute("thesis", thesis);
			return "thesis-apply-page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/addNew/{thesisId}")
	public String addNewApplication(@PathVariable("thesisId") long thesisId, Model model) throws Exception {
		logger.debug("Method: addNewApplication");
		Thesis thesis = thesisService.getThesisById(thesisId);
		if (thesis == null) {
			model.addAttribute("error", "Thesis not found with ID: " + thesisId);
			return "error-page";
		}
		ArrayList<Student> students = (ArrayList<Student>) studentService.findAll();
		model.addAttribute("students", students);
		model.addAttribute("thesis", thesis);
		ThesisApplication application = new ThesisApplication();
		application.setThesis(thesis);
		System.out.println(application);
		model.addAttribute("application", application);

		return "application-add-page";
	}

	@PostMapping("/addNew/{thesisId}")
	public String addNewThesisApplication(@Valid @ModelAttribute ThesisApplication application,
			@PathVariable("thesisId") long thesisId, BindingResult result, Model model) throws Exception {
		logger.debug("Method: addNewThesisApplication");
		Thesis thesis = thesisService.getThesisById(thesisId);
		application.setThesis(thesis);
		if (!result.hasErrors()) {
			try {
				ThesisApplication savedApplication = applicationService.insertNewThesisApplication(
						application.getThesis(), application.getStudent(), application.getAim(),
						application.getTasks());
				thesis.setApplications(thesis.getApplications() + 1);
				thesisService.updateThesisById(thesisId, thesis);
				return "redirect:/student/application/" + savedApplication.getIdta() + "/"
						+ savedApplication.getThesis().getIdt() + "/" + savedApplication.getStudent().getIdp();

			} catch (Exception e) {

				model.addAttribute("error", e.getMessage());
				logger.error(e.getMessage());
				return "error-page";
			}
		} else {
			ArrayList<Student> students = (ArrayList<Student>) studentService.findAll();
			model.addAttribute("students", students);
			model.addAttribute("thesis", application.getThesis());
			model.addAttribute("application", application);
			logger.warn("Incorrect apllication details provided");
			return "application-add-page";
		}
	}
	
	
	@GetMapping("/application/{applicationId}/{thesisId}/{studentId}")
	public String applicationSuccessful(@PathVariable("applicationId") long applicationId,
			@PathVariable("thesisId") long thesisId, @PathVariable("studentId") long studentId, Model model)
			throws Exception {
		logger.debug("Method: applicationSuccessful");
		try {
			ThesisApplication thesisApplication = applicationService.getThesisApplicationById(applicationId);
			Thesis thesis = thesisService.getThesisById(thesisId);
			Student student = studentService.getStudentById(studentId);
			if (thesisApplication == null) {
				logger.warn("Thesis application not found with ID: " + applicationId);
				throw new Exception("Thesis application not found with ID: " + applicationId);
			}
			model.addAttribute("thesis", thesis);
			model.addAttribute("student", student);
			model.addAttribute("thesisApplication", thesisApplication);
			System.err.println("\n\n " + thesisApplication);
			logger.warn("Application id: " + thesisApplication.getIdta() + " submitted successfully");
			return "application-success-student";
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

}