package com.Supaview.SpringDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Supaview.SpringDemo.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndPassword(String username, String password);

}
