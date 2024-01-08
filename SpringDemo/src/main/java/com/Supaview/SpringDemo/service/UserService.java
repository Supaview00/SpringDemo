package com.Supaview.SpringDemo.service;

import java.util.List;

import com.Supaview.SpringDemo.controller.request.UserRequest;
import com.Supaview.SpringDemo.model.User;

public interface UserService {

	User login(UserRequest userRequest);

	User register(UserRequest userRequest);

	User findUser(long id);

	List<User> findAllUser();
}
