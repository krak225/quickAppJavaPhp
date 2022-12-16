package com.krak.rhweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.krak.rhweb.model.Employe;
import com.krak.rhweb.service.EmployeService;

import lombok.Data;

@Data
@Controller
public class EmployeController {

	@Autowired
	private EmployeService service;
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employe> listEmploye = service.getEmployes();
		model.addAttribute("employes", listEmploye);
		
		return "home";
	}
	
	@GetMapping("/createEmploye")
	public String createEmploye(Model model) {
		Employe e = new Employe();
		model.addAttribute("employe", e);
		
		return "formNewEmploye";
	}
	
	@GetMapping("/updateEmploye/{id}")
	public String updateEmploye(@PathVariable("id") final int id, Model model) {
		Employe e = service.getEmploye(id);
		model.addAttribute("employe", e);
		
		return "formUpdateEmploye";		
	}
	
	@GetMapping("/deleteEmploye/{id}")
	public ModelAndView deleteEmploye(@PathVariable("id") final int id) {
		service.deleteEmploye(id);
		
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping("/saveEmploye")
	public ModelAndView saveEmploye(@ModelAttribute Employe employe) {
		if(employe.getId() != 0) {//null replaced by 0
			// Employe from update form has the password field not filled,
			// so we fill it with the current password.
			Employe current = service.getEmploye(employe.getId());
			employe.setPassword(current.getPassword());
		}
		service.saveEmploye(employe);
		
		return new ModelAndView("redirect:/");	
	}
	
}