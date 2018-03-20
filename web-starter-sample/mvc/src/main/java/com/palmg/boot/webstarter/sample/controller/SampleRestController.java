package com.palmg.boot.webstarter.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmg.boot.webcore.annotation.contorller.ReturnBind;
import com.palmg.boot.webstarter.sample.entity.User;
import com.palmg.boot.webstarter.sample.service.UserService;

/**
 * 使用@RestController处理请求时，会使用自定义的{@link com.palmg.boot.webcore.controller.ResultReturnValueHandler}处理返回参数。
 * 默认会使用{@link com.palmg.boot.webcore.controller.Result}进行一次包装。
 * @author chenkui
 *
 */
@RestController
@RequestMapping("/api/user")
public class SampleRestController {

	@Autowired
	UserService userService;
	
    @RequestMapping("/check")
    @ReturnBind(ReturnBind.Type.None) //使用这个注解，不进行返回主力。默认情况下会加一个外部包裹类{code: msg: data}。
    public String check() {
    	return "Success";
    }
    
    @RequestMapping("/id/{id}")
    //返回单个值
    public User getOneUserById(@PathVariable String id) {
    	return userService.queryById(Long.valueOf(id));
    }

    //返回列表
    //如果返回的类型为一个列表，那么返回的值会增加一个count参数，表示返回的个数。
    @RequestMapping("/all")
    public List<User> getAllUser() {
    	return userService.queryAll();
    }

    @RequestMapping("/page/{current}")
    public Page<User> getUserByPage(@PathVariable int current){
    	return userService.queryPage(current);
    }
}
