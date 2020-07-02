package com.joelmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller; *** DO WE NEED THIS ***
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping; *** DO WE NEED THIS ***
import org.springframework.web.bind.annotation.RestController;


import com.joelmvc.models.Work;

@RestController
public class WorkController {
	
	@Autowired
	WorkRepository dao;
	
	@GetMapping("/api/work")
	public List<Work> getWorks() {
		List<Work> foundWorks = dao.findAll();
		return foundWorks;
	}
	
	@GetMapping("/api/work/{id}")
	public ResponseEntity<Work> getWork(@PathVariable("id") Integer id) {
		Work foundWork = dao.findById(id).orElse(null);
		
		if(foundWork == null) {
			return ResponseEntity.notFound().header("Work", "Id not found").build();
		}
		return ResponseEntity.ok(foundWork);
	}
	
	@PostMapping("/api/work")
	public ResponseEntity<Work> postWork(@RequestBody Work work) {
		
		// saving to DB using instance of the repo interface
		Work createdWork = dao.save(work);
		
		// RespEntity crafts response to include correct status codes.
		return ResponseEntity.ok(createdWork);
	}
	
	@DeleteMapping("/api/work/{id}")
	public ResponseEntity<Work> deleteWork(@PathVariable(value="id") Integer id) {
		Work foundWork = dao.findById(id).orElse(null);
		
		if(foundWork == null) {
			return ResponseEntity.notFound().header("Work", "Id not found").build();
		}else {
			dao.delete(foundWork);
		}
		return ResponseEntity.ok().build();
	}
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;
	
}
