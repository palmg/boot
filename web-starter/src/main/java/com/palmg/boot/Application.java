package com.palmg.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmg.boot.webcore.dao.UserRepository;
import com.palmg.boot.webcore.entity.User;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.palmg.boot",excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
public class Application {}


/*@RestController
class Controller{
	
	@Autowired
	private UserRepository dao;
	
    @RequestMapping("/greeting/{data}")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name, @PathVariable String data) {
    	List<User> list = dao.findByName("123");
    	return "su";
    }
}*/
/*
@Component
class ApplicationConfig{
	@Value("${key1}")
	private String key1;
}*/