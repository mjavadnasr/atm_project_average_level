package com.example.atmversion2.controller;


import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Person;
import com.example.atmversion2.service.impl.AccountServiceImpl;
import com.example.atmversion2.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private AccountServiceImpl accountService;



    @GetMapping("/remain")
    public ResponseEntity<?> getAccountBalance(HttpServletRequest request, HttpServletResponse response, @RequestBody String accountNumber) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        Double amount = 0.0;


        boolean isMyAccount = false;
        for (Account account : person.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                isMyAccount = true;
                amount = account.getBalance();
                break;
            }
        }

        if (!isMyAccount) {
            return ResponseEntity.ok("THIS ACCOUNT NUMBER IS NOT YOURS");
        } else {
            return ResponseEntity.ok().body(amount);
        }
    }
}
