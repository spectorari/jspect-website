package com.joelmvc.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joelmvc.models.Work;

@RestController
public class UserController {

  @Autowired
  private MySQLUserDetailsService userService;
  
  @Autowired
  UserRepository dao;
  
	@GetMapping("/api/user")
	public List<User> getUsers() {
		List<User> foundUsers = dao.findAll();
		return foundUsers;
	}
	@GetMapping("/api/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User foundUser = dao.findById(id).orElse(null);
		
		if(foundUser == null) {
			return ResponseEntity.notFound().header("User", "Id not found").build();
		}
		return ResponseEntity.ok(foundUser);
	}
	
	@PutMapping("/api/user/{id}")
	  User replaceUser(@RequestBody User newUser, @PathVariable long id) {

	    return dao.findById(id)
	      .map(user -> {
	        user.setUsername(newUser.getUsername());
	        user.setPassword(newUser.getPassword());
	        return dao.save(newUser);
	      })
	      .orElseGet(() -> {
	        newUser.setId(id);
	        return dao.save(newUser);
	      });
	  } 

  @PostMapping("/api/user/register")
  public void register(@RequestBody User newUser) {
    userService.Save(newUser);
  }
   @DeleteMapping("/api/user/{id}")
   public ResponseEntity<User> deleteUser(@PathVariable(value="id") long id) {
		User foundUser = dao.findById(id).orElse(null);
		
		if(foundUser == null) {
			return ResponseEntity.notFound().header("User", "Id not found").build();
		}else {
			dao.delete(foundUser);
		}
		return ResponseEntity.ok().build();	
  }
}