package com.joelmvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.joelmvc.models.Work;

@RestController
@RequestMapping({"/api/work"})
public class WorkController {
	
	@Autowired
	WorkRepository dao;
	
	@GetMapping("/api/work")
	public List<Work> getWorks() {
		List<Work> foundWorks = dao.findAll();
		return foundWorks;
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
                newWork.setYear(rs.getInt("year"));
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
