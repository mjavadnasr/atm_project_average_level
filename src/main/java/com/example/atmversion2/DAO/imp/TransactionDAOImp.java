package com.example.atmversion2.DAO.imp;

import com.example.atmversion2.DAO.TransactionDAO;
import com.example.atmversion2.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository

public class TransactionDAOImp implements TransactionDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addTransaction(Transaction transaction) {
        hibernateTemplate.save(transaction);
    }


    public List<?> lastTenTransactions(Long id) {
        hibernateTemplate.setMaxResults(10);
        return hibernateTemplate.find("from Transaction t where t.depositor='" + id + "' or t.receiver='" + id + "'order by t.id  desc ");
    }


}
