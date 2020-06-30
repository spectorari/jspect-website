package com.joelmvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller; *** DO WE NEED THIS ***
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping; *** DO WE NEED THIS ***
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.joelmvc.models.Work;

@RestController
//@RequestMapping({"/api/work"}) ** DELETE LATER **
public class WorkController {
	
	@Autowired
	WorkRepository dao;
	
	@GetMapping("/work")
	public List<Work> getWorks() {
		List<Work> foundWorks = dao.findAll();
		return foundWorks;
	}
	
	@GetMapping("/work/{id}")
	public ResponseEntity<Work> getWork(@PathVariable("id") Integer id) {
		Work foundWork = dao.findById(id).orElse(null);
		
		if(foundWork == null) {
			return ResponseEntity.notFound().header("Work", "Id not found").build();
		}
		return ResponseEntity.ok(foundWork);
	}
	
	@PostMapping("/work")
	public ResponseEntity<Work> postWork(@RequestBody Work work) {
		
		// saving to DB using instance of the repo interface
		Work createdWork = dao.save(work);
		
		// RespEntity crafts response to include correct status codes.
		return ResponseEntity.ok(createdWork);
	}
	
	@DeleteMapping("/work/{id}")
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
	
	@GetMapping()
	public String getAllWork(Model model) {
		List<Work> works = new ArrayList<Work>();
		//Code to query database and add works to the List will go here
        Connection con;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM work");
            while (rs.next()) {
                // create a new Work object
                Work newWork = new Work();
                // get the values from each column of the current row and add them to the new Album
                newWork.setId(rs.getInt("id"));
                newWork.setTitle(rs.getString("title"));
                newWork.setImgUrl(rs.getString("img_url"));

                // add the new work to the works list
                works.add(newWork);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		model.addAttribute("works", works);
		return "works";
	}
}
