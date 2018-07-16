package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserErrorType;
import com.example.demo.repository.UserJpaRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserJpaRepository  userJpaRepository;

	
	/*    /////////////////GET ALL USERS///////////////////////*/ 
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
	List<UserDTO> users = userJpaRepository.findAll();
	if(users.isEmpty())	{
		return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
	}

	return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}
	
	/*    /////////////////CREATE USER///////////////////////*/ 

	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {

		if(userJpaRepository.findByName(user.getName()) != null)
		{	return new ResponseEntity<UserDTO>(new UserErrorType("User with "+user.getName()+" already exists"), HttpStatus.CONFLICT);}

		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	
	
	/*    /////////////////CREATE LIST USER///////////////////////*/ 
	@PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> createUserList(@RequestBody final List<UserDTO> users) {
	
		for(UserDTO user :users) {
			userJpaRepository.save(user);
		}
	return new ResponseEntity<List<UserDTO>>(users, HttpStatus.CREATED);
	}
	
	
	/*    /////////////////GET USER///////////////////////*/ 

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable final Long id) {
	UserDTO user = userJpaRepository.findById(id).orElse(null);
	if (user == null) {
		return new ResponseEntity<UserDTO>(new UserErrorType("User with id "+ id + " not found"), HttpStatus.NOT_FOUND);
		}
	return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	
	/*    /////////////////UPDATE USER///////////////////////*/ 

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable final Long id, @RequestBody UserDTO userDTO) {
	
	UserDTO user = userJpaRepository.findById(id).orElse(null);
	if (user == null) {
		return new ResponseEntity<UserDTO>(new UserErrorType("User with id "+ id + " not found"), HttpStatus.NOT_FOUND);
		}
	user.setName(userDTO.getName());
	user.setAddress(userDTO.getAddress());
	user.setEmail(userDTO.getEmail());
	
	 userJpaRepository.saveAndFlush(user);
	
	return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
		
		UserDTO user = userJpaRepository.findById(id).orElse(null);
		if (user == null) {
			return new ResponseEntity<UserDTO>(new UserErrorType("User with id "+ id + " not found"), HttpStatus.NOT_FOUND);
			}
		userJpaRepository.delete(user);

		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}


	
	public UserJpaRepository getUserJpaRepository() {
		return userJpaRepository;
	}


}
