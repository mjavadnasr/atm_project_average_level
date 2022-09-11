package com.example.atmversion2;

import com.example.atmversion2.entity.Account;
import com.example.atmversion2.entity.Person;
import com.example.atmversion2.service.impl.AccountServiceImpl;
import com.example.atmversion2.service.impl.PersonServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)


public class AtmVersion2Application {

    public static void main(String[] args) {
        SpringApplication.run(AtmVersion2Application.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(PersonServiceImpl personService, AccountServiceImpl accountService) {
        return args -> {

            personService.addPerson(new Person("javad", "javad", "javad", "javad", new ArrayList<>()));
            personService.addPerson(new Person("ali", "ali", "ali", "ali", new ArrayList<>()));

            accountService.addAccount(new Account("javad", 100000000));
            accountService.addAccount(new Account("javad2"));
            accountService.addAccount(new Account("javad3"));
            accountService.addAccount(new Account("javad4"));

            accountService.addAccount(new Account("ali", 100000000));
            accountService.addAccount(new Account("ali1"));
            accountService.addAccount(new Account("ali2"));

            personService.addAccountToPerson("javad", "javad");
            personService.addAccountToPerson("javad", "javad2");
            personService.addAccountToPerson("javad", "javad3");
            personService.addAccountToPerson("javad", "javad4");


            personService.addAccountToPerson("ali", "ali");
            personService.addAccountToPerson("ali", "ali1");
            personService.addAccountToPerson("ali", "ali2");


        };
    }
}
