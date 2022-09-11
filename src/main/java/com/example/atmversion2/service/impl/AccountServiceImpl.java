package com.example.atmversion2.service.impl;

import com.example.atmversion2.DAO.imp.AccountDAOImp;
import com.example.atmversion2.entity.Account;
import com.example.atmversion2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAOImp accountDAO;

    @Override
    public void addAccount(Account account) {
        accountDAO.addAccount(account);
    }

    @Override
    public List<Account> allAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    public List<?> findByAccountNumber(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber);
    }



}
