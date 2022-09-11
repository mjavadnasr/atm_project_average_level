package com.example.atmversion2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private String depositor;
    private String receiver;
    private double amount;
    private String type;

}
