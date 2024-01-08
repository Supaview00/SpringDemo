package com.Supaview.SpringDemo.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Supaview.SpringDemo.controller.request.UserRequest;
import com.Supaview.SpringDemo.model.User;
import com.Supaview.SpringDemo.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;

	}

	@GetMapping("/users")
	public String getusers(Model model) {
		List<User> lUser = userService.findAllUser();
		model.addAttribute("users", lUser);
		return "userList";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/registersubmit")
	public String register(@Valid UserRequest userRequest, BindingResult bindingResult) {

		userService.register(userRequest);
		return "redirect:/users";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/loginsubmit")
	public String loginSubmit(UserRequest userRequest) {
		userService.login(userRequest);

		return "redirect:/users";
	}

}
