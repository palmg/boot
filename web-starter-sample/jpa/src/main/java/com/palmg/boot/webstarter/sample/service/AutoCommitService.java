package com.palmg.boot.webstarter.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmg.boot.webstarter.sample.dao.AddressDao;
import com.palmg.boot.webstarter.sample.dao.UserDao;
import com.palmg.boot.webstarter.sample.entity.User;

@Service
/**
 * 演示CRUD操作的service
 * @author chenkui
 */
public class AutoCommitService {
	static Logger LOG = LoggerFactory.getLogger(AutoCommitService.class);
	
	@Autowired
	UserDao dao;
	
	@Autowired
	AddressDao ydao;
	
	@PostConstruct
	public void init() {
		LOG.info("MyComponent init success!");
		LOG.info(dao.toString());
	}
	
	public long addOne() {
		User user = new User("Palmg","Guangzhou");
		user = dao.save(user);
		return user.getId();
	}
	
	public long[] addMulit() {
		List<User> list = new ArrayList<>();
		list.add(new User("Aaron","ShenZhen"));
		list.add(new User("Vertx","ChengDu"));
		list = dao.save(list);
		long[] ids = new long[list.size()];
		int count = 0;
		for(User user: list) {
			ids[count++] = user.getId();
		}
		return ids;
	}
	
	public User modify(User user) {
		return dao.save(user);
	}
	
	public User queryById(long id) {
		return dao.findOne(id);
	}
	
	public List<User> queryAll() {
		return dao.findAll();
	}
	
	public void deleteById(long id) {
		dao.delete(id);
	}
	
	public void deleteAll() {
		dao.deleteAll();
	}
}
