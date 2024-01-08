package com.Supaview.SpringDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Supaview.SpringDemo.controller.request.UserRequest;
import com.Supaview.SpringDemo.exception.UserDuplicateException;
import com.Supaview.SpringDemo.exception.UserNotFoundException;
import com.Supaview.SpringDemo.model.User;
import com.Supaview.SpringDemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public User register(UserRequest userRequest) {
		Optional<User> chkuser = userRepository.findByUsername(userRequest.getUsername());
		if (chkuser.isPresent()) {
			throw new UserDuplicateException(userRequest.getUsername());
		}
		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());

		return userRepository.save(user);
	}

	@Override
	public User findUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UserNotFoundException(id);

	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User login(UserRequest userRequest) {
		Optional<User> user = userRepository.findByUsernameAndPassword(userRequest.getUsername(),
				userRequest.getPassword());
		if (user.isPresent()) {
			return user.get();
		}
		throw new UserNotFoundException("username or password is incorrect");

	}
}
