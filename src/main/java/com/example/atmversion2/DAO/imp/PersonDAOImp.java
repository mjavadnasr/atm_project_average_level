package com.example.atmversion2.DAO.imp;

import com.example.atmversion2.DAO.PersonDAO;
import com.example.atmversion2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PersonDAOImp implements PersonDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    public void addPerson(Person person) {
        hibernateTemplate.save(person);

    }

    public List<Person> getAllPersons() {
        return hibernateTemplate.loadAll(Person.class);
    }

    public List<?> getByUsername(String username) {
        return hibernateTemplate.find("from Person p where p.username = '" + username + "'");
    }


}
