package com.palmg.boot.webstarter.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmg.boot.webstarter.sample.dao.MyRepository;
import com.palmg.boot.webstarter.sample.entity.User;

@Service
public class MyService {
	static Logger LOG = LoggerFactory.getLogger(MyService.class);
	
	@Autowired
	MyRepository dao;
	
	@PostConstruct
	public void init() {
		LOG.info("MyComponent init success!");
		LOG.info(dao.toString());
	}
	
	public void addOneRaw() {
		User user = new User("Palmg","Guangzhou");
		dao.save(user);
	}
	
	public void addMulitRaw() {
		List<User> list = new ArrayList<>();
		list.add(new User("Palmg","Guangzhou"));
		list.add(new User("Palmg","Guangzhou"));
		dao.save(list);
	}
}
