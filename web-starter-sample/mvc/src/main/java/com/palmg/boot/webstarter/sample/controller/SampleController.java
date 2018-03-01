package com.palmg.boot.webstarter.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmg.boot.webstarter.sample.entity.User;
import com.palmg.boot.webstarter.sample.service.UserService;

@RestController
@RequestMapping("/api/user")
public class SampleController {

	@Autowired
	UserService userService;
	
    @RequestMapping("/check")
    public String check() {
    	return "Success";
    }
    
    @RequestMapping("/id/{id}")
    public User getOneUserById(@PathVariable String id) {
    	return userService.queryById(Long.valueOf(id));
    }

    @RequestMapping("/all")
    public List<User> getAllUser() {
    	return userService.queryAll();
    }
}
