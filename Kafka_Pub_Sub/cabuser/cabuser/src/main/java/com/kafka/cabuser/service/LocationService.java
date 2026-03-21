package com.kafka.cabuser.service;

//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

//    @KafkaListener(topics = "cab-location", groupId = "user-group")
    public void cablocation(String location){
        System.out.println(location);
    }
}
