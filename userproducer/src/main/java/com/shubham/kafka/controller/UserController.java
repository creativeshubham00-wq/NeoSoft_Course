package com.shubham.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.kafka.dto.User;
import com.shubham.kafka.service.UserProducerService;

@RestController
@RequestMapping("/userapi")
public class UserController {

	@Autowired
	private UserProducerService service;

	@PostMapping("/publishUserData")
	public void sendUserData(@RequestBody User user) {
		service.sendUserData(user);
	}
}
