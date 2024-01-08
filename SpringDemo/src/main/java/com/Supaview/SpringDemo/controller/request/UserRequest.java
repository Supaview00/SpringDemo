package com.Supaview.SpringDemo.controller.request;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	@NotEmpty(message = "Username must not empty")
	@Size(max = 30, min = 4)
	private String username;

	@Length(max = 30, min = 4, message = "{Atleast {min} Characters")
	private String password;
}
