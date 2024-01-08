package com.Supaview.SpringDemo.controller.restApi;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Supaview.SpringDemo.controller.request.UserRequest;
import com.Supaview.SpringDemo.model.User;
import com.Supaview.SpringDemo.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;

	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/register")
	public User register(@Valid UserRequest userRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(field -> {
				throw new ValidationException(field.getField() + " : " + field.getDefaultMessage());
			});
		}
		return userService.register(userRequest);
	}

	@GetMapping("/{id}")
	public User findUser(@PathVariable long id) {
		return userService.findUser(id);
	}

}
