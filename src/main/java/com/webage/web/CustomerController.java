package com.webage.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webage.domain.Customer;
import com.webage.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/index.html")
	public String getIndex() {
		System.out.println("getIndex was called");
		return "/index";
	}
	
	@GetMapping("/") 
	public String getRoot() {
		return "redirect:/index.html";
	}
	
	@ModelAttribute("date")
	public Date getDate() {
		return new Date();
	}
	

	@RequestMapping("/browse")
	public ModelAndView browseCustomers() {
		Iterable<Customer> list =
			customerService.findAllCustomers();
		return new ModelAndView("browseCustomers",
			 "customerList", list);
	}
}
