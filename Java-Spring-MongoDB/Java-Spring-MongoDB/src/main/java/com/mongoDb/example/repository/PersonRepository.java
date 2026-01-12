package com.mongoDb.example.repository;

import com.mongoDb.example.collection.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByFirstNameStartsWith(String firstName);

    List<Person> findByAgeBetween(Integer min, Integer max);

    @Query(value = "{ 'age' : { $gt: ?0, $lt: ?1 }}")
    List<Person> findPersonByAgeBetween(Integer min, Integer max);
}
