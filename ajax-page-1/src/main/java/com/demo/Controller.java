package com.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	service service;
	@Autowired
	UserRepository repo;
	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {	     
	    Page<User> page = service.listAll(pageNum, sortField, sortDir);	     
	    List<User> listProducts = page.getContent();
	    model.addAttribute("totalPages",page.getTotalPages());
	    model.addAttribute("totalItems",page.getTotalElements());
	    model.addAttribute("currentPage", pageNum);    
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());     
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");	     
	    model.addAttribute("listProducts", listProducts);
	    System.out.println(pageNum);
	    return "index";
	}
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return viewPage(model, 1, "name", "asc");
	}
}
