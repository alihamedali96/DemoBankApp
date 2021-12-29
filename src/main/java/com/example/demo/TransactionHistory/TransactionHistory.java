/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.TransactionHistory;

import com.example.demo.AppUser.AppUser;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class TransactionHistory {
       @SequenceGenerator( 
            name="Transaction_sequence", 
            sequenceName="Transaction_sequence", 
            allocationSize =1
            )
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Transaction_sequence")
    private long id;
       
    private long account_id;
   // private long user_id;
    private String transaction_type;
    private double amount;
    private String  status;
    private String  reason_code;
    private LocalDateTime created_at;
    
}
