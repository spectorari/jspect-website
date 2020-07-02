package com.joelmvc.controllers;
// May Want to Delete Home Controller
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joelmvc.models.Work;

@Controller
@RequestMapping({"/","home"})
public class HomeController {

		@GetMapping()
		public String index(Model model) {
			model.addAttribute("work", new Work());
			// the string index will be looked for in src/main/resources/templates
			return "index";
		}
		
//			//Ctrl-Shift-O to auto import
//		@PostMapping("/work")
//		public String workSubmit(@ModelAttribute Work work) {
//		return "result";
//		}
}
