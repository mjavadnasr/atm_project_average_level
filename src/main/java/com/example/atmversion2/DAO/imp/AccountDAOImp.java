package com.example.atmversion2.DAO.imp;

import com.example.atmversion2.DAO.AccountDAO;
import com.example.atmversion2.entity.Account;
import com.example.atmversion2.model.Queries;
import com.example.atmversion2.model.Query;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Transactional
@Repository
public class AccountDAOImp implements AccountDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addAccount(Account account) {
        hibernateTemplate.save(account);
    }

    @SneakyThrows
    public List<?> findByAccountNumber(String accountNumber) {
        String query = findQuery("findByAccountNumber");
        return hibernateTemplate.find(query, new String[]{accountNumber});
    }

    @SneakyThrows
    public List<?> findById(Long id) {
        String query = findQuery("findById");
        return hibernateTemplate.find(query, new Long[]{id});
    }

    public List<Account> getAllAccounts() {
        return hibernateTemplate.loadAll(Account.class);
    }


    public String findQuery(String key) throws JAXBException {

        File file = new File("src/main/resources/query/query.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Queries.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Queries rootEl = (Queries) jaxbUnmarshaller.unmarshal(file);

        for (Query query : rootEl.getQueries()) {
            if (query.getKey().equals(key))
                return query.getValue();
        }
        return null;
    }
}
