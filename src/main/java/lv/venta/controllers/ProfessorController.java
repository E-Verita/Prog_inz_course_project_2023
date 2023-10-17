package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

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
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;
import lv.venta.services.IAcademicPersonelService;
import lv.venta.services.IStudentService;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	

	@Autowired
	private IThesisService thesisService;
	
	@Autowired
    private IAcademicPersonelService academicService;
	
	@Autowired
    private IStudentService studentService;
	
	private ArrayList<Area> areas = new ArrayList<>(Arrays.asList(Area.values()));
	private ArrayList<Complexity> complexities = new ArrayList<>(Arrays.asList(Complexity.values()));
	
	@GetMapping("/showAll")
	public String showAllThesis(Model model) {
	    try {
	        ArrayList<Thesis> thesis = thesisService.selectAllThesis();
	        if (thesis.isEmpty()) {
	            throw new Exception("No theses found!");
	        }
			model.addAttribute("thesis", thesis);
			model.addAttribute("searchedElement", "All Thesis");
	        return "thesis-all-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	@GetMapping("/showAll/{id}")
	public String showThesisBSupervisorId(@PathVariable long id, Model model) {
		try {
	        ArrayList<Thesis> thesis = thesisService.selectAllThesisBySupervisor(id);
	        if (thesis.isEmpty()) {
	            throw new Exception("No theses found for Supervisor ID:" + id );
	        }
			model.addAttribute("thesis", thesis);
			model.addAttribute("searchedElement", " Supervisor " + id);
			return "thesis-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}


	@GetMapping("/addNew")
	public String showAddThesisForm(Model model) throws Exception {
		//ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicService.findAll();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
		ArrayList<AcademicPersonel> supervisor = (ArrayList<AcademicPersonel>) academicService.findByUsername(currentPrincipalName);
    	System.out.println(currentPrincipalName);
        model.addAttribute("supervisors", supervisor); //-s
		model.addAttribute("thesis", new Thesis());
		model.addAttribute("areas", areas);
		model.addAttribute("complexities", complexities);
		return "thesis-add-page";
	}

	
	@PostMapping("/addNew")
	public String addNewThesis(@Valid @ModelAttribute Thesis thesis, BindingResult result, Model model) throws Exception {
	    if (!result.hasErrors()) {
	        try {
	        	thesisService.insertNewThesis(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAreas(), thesis.getComplexity(), 
	        			thesis.getPublicNotes(), thesis.getSupervisor());
	            return "redirect:/professor/showAll/" + thesis.getSupervisor().getIdp();
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	    	 ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicService.findAll();
	         model.addAttribute("supervisors", supervisors);
	         model.addAttribute("areas", areas);
	         model.addAttribute("complexities", complexities);
	        return "thesis-add-page";
	    }
	    
	}
	
	@GetMapping("/show/{id}")
	public String showThesisById(@PathVariable long id, Model model) {
	    try {
	        Thesis thesis = thesisService.getThesisById(id);
	        if (thesis == null) {
	            throw new Exception("Thesis not found with ID: " + id);
	        }
	        model.addAttribute("thesis", thesis);
	        return "thesis-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	@GetMapping("/remove/{id}")
	public String removeThesisById(@PathVariable("id") long id, Model model) {
		try {
			thesisService.deleteThesisById(id);
			model.addAttribute("thesis", thesisService.selectAllThesis());
			return "redirect:/professor/showAll";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/remove/{id}")
	public String removeThesisByIdRedirected(@PathVariable("id") long id, Model model) {
		try {
			thesisService.deleteThesisById(id);
			model.addAttribute("thesis", thesisService.selectAllThesis());
			return "redirect:/professor/showAll";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/update/{id}")
	public String showUpdateThesisForm(@PathVariable long id, Model model) {
	    try {
	        Thesis thesis = thesisService.getThesisById(id);
	        model.addAttribute("thesis", thesis);
			//ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicService.findAll();
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String currentPrincipalName = authentication.getName();
			ArrayList<AcademicPersonel> supervisor = (ArrayList<AcademicPersonel>) academicService.findByUsername(currentPrincipalName);
		     model.addAttribute("supervisors", supervisor);
	         model.addAttribute("areas", areas);
	         model.addAttribute("complexities", complexities);
	         ArrayList<Student> students = (ArrayList<Student>) studentService.findAll();
	         model.addAttribute("students", students);
	        return "thesis-update-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	@PostMapping("/update/{id}")
	public String updateThesisById(@PathVariable long id, @Valid @ModelAttribute Thesis thesis, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        try {
	        	thesisService.updateThesisById(id, thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAreas(), thesis.getComplexity(), 
	        			thesis.getPrivateNotes(), thesis.getPublicNotes(), thesis.getAssignedStudent(), thesis.getSupervisor());
	            return "redirect:/professor/show/" + id;
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	        return "driver-update-page";
	    }
	}
}
