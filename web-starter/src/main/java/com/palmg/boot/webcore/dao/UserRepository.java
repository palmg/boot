package com.palmg.boot.webcore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palmg.boot.webcore.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
}
