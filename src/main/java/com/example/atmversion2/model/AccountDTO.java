package com.example.atmversion2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AccountDTO {

    private String accountNumber;
    private double balance;

}
