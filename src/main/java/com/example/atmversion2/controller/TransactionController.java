package com.example.atmversion2.controller;

import com.example.atmversion2.model.TransactionDTO;
import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Person;
import com.example.atmversion2.service.impl.AccountServiceImpl;
import com.example.atmversion2.service.impl.PersonServiceImpl;
import com.example.atmversion2.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    public TransactionServiceImpl transactionService;

    @Autowired
    public AccountServiceImpl accountService;

    @Autowired
    public PersonServiceImpl personService;


    @PostMapping("/normal")
    public ResponseEntity<String> save(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, transactionDTO.getDepositor()))
            return ResponseEntity.ok().body("THIS IS NOT YOUR ACCOUNT");

        String message = transactionService.addTransaction(transactionDTO, username, 10000000);

        return ResponseEntity.ok().body(message);
    }


    @PostMapping("/paya")
    public ResponseEntity<?> savePaya(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, transactionDTO.getDepositor()))
            return ResponseEntity.ok().body("THIS IS NOT YOUR ACCOUNT");

        String message = transactionService.addTransaction(transactionDTO, username, 50000000);

        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/lastTransactions")
    public ResponseEntity<?> getLastTenTransactions(@RequestBody String accountNumber, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = PersonController.getUsernameByToken(request, response);
        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, accountNumber))
            return ResponseEntity.ok().body("THIS IS NOT YOUR ACCOUNT");

        List accounts = accountService.findByAccountNumber(accountNumber);
        Long id = ((Account) accounts.get(0)).getId();
        List transactions = transactionService.lastTenTransactions(id);


        return ResponseEntity.ok().body(transactions);
    }


    public Boolean checkIsMyAccount(Person person, String depositorNumber) {
        boolean isMyAccount = false;

        for (Account account : person.getAccounts()) {
            if (account.getAccountNumber().equals(depositorNumber)) {
                isMyAccount = true;
                break;
            }
        }

        if (!isMyAccount)
            return false;
        return true;
    }

}
