package com.example.atmversion2.service;

import com.example.atmversion2.entity.Account;

import java.util.List;

public interface AccountService {

    void addAccount(Account account);

    List<Account> allAccounts();

    List<?> findByAccountNumber(String accountNumber);

}
