package com.shubham.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.shubham.kafka.dto.User;

@Service
public class UserConsumerService {

	@KafkaListener(topics = { "user-topic" })
	public void consumeUserData(User user) {
		System.out.println("Users Age is : " + user.getAge() + " fav genre : " + user.getFavGenre());
	}
}
