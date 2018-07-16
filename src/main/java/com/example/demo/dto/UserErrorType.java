package com.example.demo.dto;

public class UserErrorType extends UserDTO{

		private String errorMessage;
		
		public UserErrorType(final String errorMessage){
		
			this.errorMessage = errorMessage;
		}
		
		@Override
		public String getErrorMessage() {
		return errorMessage;
		
		}
}
