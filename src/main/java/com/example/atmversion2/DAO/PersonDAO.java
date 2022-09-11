package com.example.atmversion2.DAO;

import com.example.atmversion2.entity.Person;

import java.util.List;

public interface PersonDAO {

    void addPerson(Person person);

    List<Person> getAllPersons();

    List<?> getByUsername(String username);
}
