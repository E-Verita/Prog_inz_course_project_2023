package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.models.Thesis;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.services.IThesisAplicationService;

@Controller
@RequestMapping("/application")
public class ThesisApplicationController {
	
	@Autowired
	private IThesisAplicationService applicationService;

	
	@PostMapping("/addNew")
	public String addNewApplications(@Valid @ModelAttribute Thesis thesis, BindingResult result, Model model) throws Exception {
	  	        return "application-add-page";
	    }
	}

