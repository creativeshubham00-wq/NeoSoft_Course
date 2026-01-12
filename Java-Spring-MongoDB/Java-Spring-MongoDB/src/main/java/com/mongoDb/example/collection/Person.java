package com.mongoDb.example.collection;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "person")
public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> hobbies;
}
