package com.example.atmversion2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account extends BaseEntity {

    @Column(name = "accountNumber", unique = true)
    private String accountNumber;

    @Column(name = "balance")
    private double balance = 100000.00;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
