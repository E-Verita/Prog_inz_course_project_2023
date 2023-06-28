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
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;
import lv.venta.repos.users.IAcademicPersonelRepo;
import lv.venta.repos.users.IStudentRepo;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/thesis")
public class ThesisController {

	@Autowired
	private IThesisService thesisService;
	
	@Autowired
    private IAcademicPersonelRepo academicRepo;
	
	@Autowired
    private IStudentRepo studentRepo;
	
	private ArrayList<Area> areas = new ArrayList<>(Arrays.asList(Area.values()));
	private ArrayList<Complexity> complexities = new ArrayList<>(Arrays.asList(Complexity.values()));
	
	@GetMapping("/hello") 
	public String helloFunc() {
		System.out.println("Mans pirmais kontrolieris ir nostrādājis");
		return "hello-page"; // tiks parādīta hello-page.html lapa
	}
	
	@GetMapping("/showAll")
	public String showAllTrips(Model model) {
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
	public String showDriverById(@PathVariable long id, Model model) {
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
	public String showAddThesisForm(Model model) {
		ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicRepo.findAll();
        model.addAttribute("supervisors", supervisors);
		model.addAttribute("thesis", new Thesis());
		model.addAttribute("areas", areas);
		model.addAttribute("complexities", complexities);
		return "thesis-add-page";
	}

	
	@PostMapping("/addNew")
	public String addNewThesis(@Valid @ModelAttribute Thesis thesis, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        try {
	        	thesisService.insertNewThesis(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAreas(), thesis.getComplexity(), 
	        			thesis.getPublicNotes(), thesis.getSupervisor());
	            return "redirect:/thesis/showAll/" + thesis.getSupervisor().getIdp();
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	    	 ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicRepo.findAll();
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
			return "redirect:/thesis/showAll";
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
			return "redirect:/thesis/showAll";
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
			ArrayList<AcademicPersonel> supervisors = (ArrayList<AcademicPersonel>) academicRepo.findAll();
		     model.addAttribute("supervisors", supervisors);
	         model.addAttribute("areas", areas);
	         model.addAttribute("complexities", complexities);
	         ArrayList<Student> students = (ArrayList<Student>) studentRepo.findAll();
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
	            return "redirect:/thesis/show/" + id;
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	        return "driver-update-page";
	    }
	}

	
}
