package com.example.atmversion2.controller;


import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Person;
import com.example.atmversion2.service.impl.AccountServiceImpl;
import com.example.atmversion2.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class TestController {

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok().body(personService.getAllPersons());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAcounts() {
        return ResponseEntity.ok().body(accountService.allAccounts());
    }


    @PostMapping("/save")
    public ResponseEntity<String> savePerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("DONE!");
    }

    @GetMapping("/app/find/{username}")
    public ResponseEntity<?> find(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(personService.getByUsername(username));
    }


}
