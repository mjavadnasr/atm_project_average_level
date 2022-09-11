package com.example.atmversion2.mapper;


import com.example.atmversion2.model.AccountDTO;
import com.example.atmversion2.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {


    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBalance(account.getBalance());

        return accountDTO;
    }

    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());

        return account;
    }

    public List<Account> toEntities(List<AccountDTO> accountDTOS) {
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOS) {
            Account account = new Account();
            account.setBalance(accountDTO.getBalance());
            account.setAccountNumber(accountDTO.getAccountNumber());
            accounts.add(account);
        }


        return accounts;
    }


    public List<AccountDTO> toDTOs(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setBalance(account.getBalance());
            accountDTOS.add(accountDTO);
        }


        return accountDTOS;
    }


}
