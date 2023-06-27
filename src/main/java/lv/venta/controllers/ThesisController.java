package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.models.Thesis;
import lv.venta.services.IThesisService;

@Controller
@RequestMapping("/thesis")
public class ThesisController {

	@Autowired
	private IThesisService thesisService;
	
	@GetMapping("/hello") 
	public String helloFunc() {
		System.out.println("Mans pirmais kontrolieris ir nostrādājis");
		return "hello-page"; // tiks parādīta hello-page.html lapa
	}
	
	@GetMapping("/showAll")
	public String showAllTrips(Model model) {
	    try {
	        ArrayList<Thesis> thesis = thesisService.selectAllThesis();
	        model.addAttribute("thesis", thesis);
			model.addAttribute("searchedElement", "All Thesis");
	        return "thesis-all-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}

}
