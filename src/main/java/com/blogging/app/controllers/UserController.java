package com.blogging.app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.app.entities.User;
import com.blogging.app.payloads.UserDTO;
import com.blogging.app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO newUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(
			@Valid @RequestBody UserDTO userDTO,
			@PathVariable int id) {
		
		userService.updateUser(userDTO, id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		UserDTO userDTO = userService.getUserById(id);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
}
