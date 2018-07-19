package com.example.demo.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("++++++++++++++++PRINCIPAL  "+user.getName());
	return user;
	}

}
