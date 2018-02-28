package com.palmg.boot.webstartexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palmg.boot.webstartexample.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
}
