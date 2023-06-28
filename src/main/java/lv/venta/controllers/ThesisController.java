package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.Thesis;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.repos.users.IAcademicPersonelRepo;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/thesis")
public class ThesisController {

	@Autowired
	private IThesisService thesisService;
	
	@Autowired
    private IAcademicPersonelRepo academicRepo;
	
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

	
}
