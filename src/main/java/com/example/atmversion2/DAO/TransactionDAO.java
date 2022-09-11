package com.example.atmversion2.DAO;

import com.example.atmversion2.entity.Transaction;

import java.util.List;

public interface TransactionDAO {

    void addTransaction(Transaction transaction);

    List<?> lastTenTransactions(Long id);
}
