package com.example.atmversion2.service.impl;

import com.example.atmversion2.DAO.imp.AccountDAOImp;
import com.example.atmversion2.DAO.imp.PersonDAOImp;
import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Person;
import com.example.atmversion2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class PersonServiceImpl implements PersonService, UserDetailsService {

    @Autowired
    PersonDAOImp personDAO;

    @Autowired
    AccountDAOImp accountDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //implementing loadUserByUsername in UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List persons = personDAO.getByUsername(username);
        if (persons == null)
            throw new UsernameNotFoundException("user not found");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));


        Person person = (Person) persons.get(0);


        return new User(person.getUsername(), person.getPassword(), authorities);
    }

    @Override
    public void addPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personDAO.addPerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Override
    public List getByUsername(String username) {
        return personDAO.getByUsername(username);
    }

    @Override
    public void addAccountToPerson(String username, String accountNumber) {
        List<?> accounts = accountDAO.findByAccountNumber(accountNumber);
        Account account = (Account) accounts.get(0);

        Person person = (Person) getByUsername(username).get(0);
        person.getAccounts().add(account);

    }

    @Override
    public Double getAllAccountBalance(Person person) {
        Double sum = 0.0;
        for (Account account : person.getAccounts()) {
            sum += account.getBalance();
        }

        return sum;
    }


}
