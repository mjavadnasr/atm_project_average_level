package com.example.atmversion2.service;

import com.example.atmversion2.entity.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);

    List<Person> getAllPersons();

    List getByUsername(String username);

    void addAccountToPerson(String username, String accountNumber);

    Double getAllAccountBalance(Person person);
}
