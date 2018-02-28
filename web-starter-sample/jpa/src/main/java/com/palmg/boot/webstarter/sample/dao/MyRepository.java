package com.palmg.boot.webstarter.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palmg.boot.webstarter.sample.entity.User;

public interface MyRepository extends JpaRepository<User, Long> {
}
