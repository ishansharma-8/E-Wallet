package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId;
    //user db will be different to transaction db as user details will be stored in user db and txn detail in txn db
    // cannot create 1 to many or many to many or relation b/w them so same details won't be stored
    //still want detail to be stored in txn db( not sure) take sender id and receiver id  and message
    private String sender;
    private String receiver;
    private String message;
    private Double amount;

    //Till the wallet is updated the trans status might be in pending state
    //how to implement txn status either Synchronous or Asynchronous
    //synchronously via direct api call and will be using Asynchronously
    //will use kafka as middle ware where kafka will notify the wallet and once the wallet is updated we will consume that event
    //we will make the changes accordingly similar to amazon pay
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updateOn;



}
