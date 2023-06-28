package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.services.IThesisAplicationService;

@Controller
@RequestMapping("/application")
public class ThesisApplicationController {
	
	@Autowired
	private IThesisAplicationService applicationService;

}
