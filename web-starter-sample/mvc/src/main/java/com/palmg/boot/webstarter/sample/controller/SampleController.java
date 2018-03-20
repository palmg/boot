package com.palmg.boot.webstarter.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class SampleController {
	
    @RequestMapping("/home")
    public String check() {
    	return "index";
    }
}
