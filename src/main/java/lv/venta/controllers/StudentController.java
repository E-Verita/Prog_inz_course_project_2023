package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
	

	@GetMapping("/apply")
	public String showAwailableThesis(Model model) throws Exception {
		 try {
		ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicService.findAll();
		ArrayList<Thesis> thesis = thesisService.selectAllByAssignedStudentIsNull();
		model.addAttribute("searchedElement", "Application");
		model.addAttribute("thesis", thesis);
        model.addAttribute("supervisors", supervisors);
		model.addAttribute("complexities", complexities);
		return "thesis-all-apply-page";
	} catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error-page";
    }
	}
	
	@GetMapping("/apply/{id}")
	public String showThesisInformation(@PathVariable("id")  long id, Model model) {
		try {
	        Thesis thesis = thesisService.getThesisById(id);
	        if (thesis == null) {
	            throw new Exception("Thesis not found with ID: " + id);
	        }
	        model.addAttribute("thesis", thesis);
	        return "thesis-apply-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	@GetMapping("/addNew/{thesisId}")
	public String addNewApplication(@PathVariable("thesisId") long thesisId, Model model) throws Exception {
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
		model.addAttribute("application", application);

		return "application-add-page";
	}

	@PostMapping("/addNew")
	public String addNewThesisApplication( @Valid @ModelAttribute ThesisApplication application, BindingResult result, Model model) throws Exception {
		if (!result.hasErrors()) {
	        try {
	            applicationService.insertNewThesisApplication(
	                    application.getThesis(),
	                    application.getStudent(),
	                    application.getAim(),
	                    application.getTasks()
	            );
	            return "redirect:/thesis/apply";
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
		    ArrayList<Student> students = (ArrayList<Student>) studentService.findAll();
	        model.addAttribute("students", students);
	        model.addAttribute("thesis", application.getThesis());
	        return "application-add-page";
	    }
	}
}