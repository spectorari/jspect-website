package com.joelmvc.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joelmvc.models.Work;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private MySQLUserDetailsService userService;
  
  @Autowired
  UserRepository dao;
  
	@GetMapping("")
	public List<User> getUsers() {
		List<User> foundUsers = dao.findAll();
		return foundUsers;
	}

  @PostMapping("/register")
  public void register(@RequestBody User newUser) {
    userService.Save(newUser);
  }
}