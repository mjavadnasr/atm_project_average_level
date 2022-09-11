package com.example.atmversion2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @Column(name = "depositor")
    private Long depositor;

    @Column(name = "receiver")
    private Long receiver;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private String type;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;


    public Transaction(Long depositor, Long receiver, double amount, String type) {
        this.depositor = depositor;
        this.receiver = receiver;
        this.amount = amount;
        this.type = type;
    }
}
