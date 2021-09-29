package com.iacsd.library.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iacsd.library.DTO.UserLogin;
import com.iacsd.library.exception.UserAlreadyExistsException;
import com.iacsd.library.model.User;
import com.iacsd.library.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	

	
	@PostMapping("/api/v1/user/register")
	public ResponseEntity<?> cretaeuser(@RequestBody User user) {
		try {
			userService.saveUser(user);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping("/")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
	
	@PostMapping("/api/v1/user/login")
	public ResponseEntity<?> login(@RequestBody UserLogin user){
		Map<String,String> map = new HashMap<>();
		try {
			User userByEmailAndPassword = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
			if (userByEmailAndPassword!=null) {
				map.put("userID", userByEmailAndPassword.getId().toString());
				map.put("email", userByEmailAndPassword.getEmail());
				map.put("token", "123456");
				map.put("role", userByEmailAndPassword.getRole());
				return new ResponseEntity<>(map,HttpStatus.OK);
			} else {
				map.clear();
				map.put("message","Invalid User");
				map.put("Token",null);
				return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			map.clear();
			map.put("message",e.getMessage());
			map.put("Token",null);
			return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@GetMapping("/api/v1/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
			User user = userService.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
}
