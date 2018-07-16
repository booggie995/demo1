package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class UserDTO {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private long id;
	
	@NotEmpty(message = "error.name.empty")	
	@Column(name="user_name")
	private String name;
	
	@NotEmpty(message = "error.email.empty")	
	@Email(message = "error.email.email")
	@Column(name="user_email")
	private String email;
	
	
	@NotEmpty(message = "error.address.empty")	
	@NotEmpty
	@Column(name="user_address")
	private String address;
	
	
	private String errorMessage;
	
	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
