package com.example.atmversion2.DAO;

import com.example.atmversion2.entity.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account account);

    List<?> findByAccountNumber(String accountNumber);

    List<?> findById(Long id);

    List<Account> getAllAccounts();
}
