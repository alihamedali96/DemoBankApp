/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Accounts;


import com.example.demo.AppUser.AppUser;
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

/**
 *
 * @author gharibaahmedsuleiman
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Accounts {
    
    @SequenceGenerator( 
            name="Accounts_sequence", 
            sequenceName="Accounts_sequence", 
            allocationSize =1
            )
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Accounts_sequence")
    private long id;
  
    private double balance;
    private double overdraft;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appuser;

    public Accounts(double balance, double overdraft, AppUser appuser) {
        this.balance = balance;
        this.overdraft = overdraft;
        this.appuser = appuser;
    }
    
    public Accounts(double balance, double overdraft,String name, AppUser appuser) {
        this.balance = balance;
        this.overdraft = overdraft;
        this.name = name;
        this.appuser = appuser;
    }
    
  
}
