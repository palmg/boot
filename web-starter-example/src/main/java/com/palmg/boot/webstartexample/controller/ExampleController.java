package com.palmg.boot.webstartexample.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmg.boot.webstartexample.dao.UserRepository;

/**
 * @RestController 注解用于处理Restfull格式的调用
 * @author chenkui
 */
@RestController
public class ExampleController {
	static Logger LOG = LoggerFactory.getLogger(ExampleController.class);

	@Autowired
	ApplicationContext context;

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void init() {
		LOG.info("Init method of ExampleController!"); 
	}

	/**
	 * @RequestMapping 注解用于标记
	 * @param name
	 *            RequestParam注解用于获取URL中的query相关参数（domain?name=data）。
	 * @param data
	 *            PathVariable 用于对应提取Restfull格式的参数，
	 * @return
	 */
	@RequestMapping("/greeting/{data}")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name,
			@PathVariable String data) {
		userRepository.findByName("123");
		return "su";
	}
}
