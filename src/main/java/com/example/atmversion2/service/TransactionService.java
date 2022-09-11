package com.example.atmversion2.service;

import com.example.atmversion2.entity.Transaction;
import com.example.atmversion2.model.TransactionDTO;

import java.util.List;

public interface TransactionService {


     String addTransaction(TransactionDTO transactionDTO , String username , double limit);

     List<?> lastTenTransactions(Long id);
}
