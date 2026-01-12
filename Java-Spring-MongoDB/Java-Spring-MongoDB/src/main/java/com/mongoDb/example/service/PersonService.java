package com.mongoDb.example.service;

import com.mongoDb.example.collection.Person;

import java.util.List;

public interface PersonService {
    Person addPerson(Person person);

    List<Person> findPersonByFirstName(String firstName);

    List<Person> findAllPerson();

    List<Person> findByAge(Integer min, Integer max);
}
