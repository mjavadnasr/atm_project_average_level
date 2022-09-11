package com.example.atmversion2.service.impl;

import com.example.atmversion2.DAO.imp.AccountDAOImp;
import com.example.atmversion2.DAO.imp.PersonDAOImp;
import com.example.atmversion2.DAO.imp.TransactionDAOImp;
import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Transaction;
import com.example.atmversion2.model.TransactionDTO;
import com.example.atmversion2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDAOImp transactionDAO;

    @Autowired
    AccountDAOImp accountDAO;
    @Autowired
    PersonDAOImp personDAO;


    //check if account have enough money . check depositor and receiver are available . if this 3 conditions are true calculate amount of each account and transaction will be saved
    @Override
    public String addTransaction(TransactionDTO transactionDTO , String username , double limit) {



        String depositorNumber = transactionDTO.getDepositor();
        String receiverNumber = transactionDTO.getReceiver();

        List depositors = accountDAO.findByAccountNumber(depositorNumber);


        if (depositors.size() == 0)
            return "CHECK YOUR ACCOUNT NUMBER AGAIN";

        Account depositor = (Account) depositors.get(0);

        List receivers = accountDAO.findByAccountNumber(receiverNumber);
        if (receivers.size() == 0)
            return "CHECK Receiver ACCOUNT NUMBER AGAIN";

        Account receiver = (Account) receivers.get(0);

        Double sum = 0.0;


        if (depositor.getBalance() <= transactionDTO.getAmount())
            return "YOU DONT HAVE ENOUGH MONEY";

        for (Transaction transaction : depositor.getTransactions()) {
            if (LocalDate.now().getDayOfMonth() == transaction.getCreatedAt().getDate())
                sum += transaction.getAmount();

        }

        sum += transactionDTO.getAmount();
        if (sum > limit)
            return "TRANSACTION LIMIT IS OVER";

        Transaction transaction = new Transaction(depositor.getId(), receiver.getId(), transactionDTO.getAmount(), transactionDTO.getType());

        if (depositor != null && receiver != null && depositor.getBalance() > transaction.getAmount()) {
            depositor.setBalance(depositor.getBalance() - transaction.getAmount());
            receiver.setBalance(receiver.getBalance() + transaction.getAmount());
            depositor.getTransactions().add(transaction);
            transactionDAO.addTransaction(transaction);

        }
        return "DONE!";

    }

    @Override
    public List<?> lastTenTransactions(Long id) {
        return transactionDAO.lastTenTransactions(id);
    }
}
