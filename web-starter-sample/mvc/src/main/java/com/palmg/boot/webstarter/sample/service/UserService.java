package com.palmg.boot.webstarter.sample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.palmg.boot.webstarter.sample.dao.UserDao;
import com.palmg.boot.webstarter.sample.entity.User;

@Service
/**
 * 演示CRUD操作的service
 * @author chenkui
 */
public class UserService {
	static Logger LOG = LoggerFactory.getLogger(Service.class);
	
	@Autowired
	UserDao userDao;
	
	public User queryById(long id) {
		return userDao.findOne(id);
	}
	
	public List<User> queryAll() {
		return userDao.findAll();
	}
	
	public Page<User> queryPage(int current){
		Pageable pageable = new PageRequest(current,10); 
		return userDao.findAll(pageable);
	}
}
