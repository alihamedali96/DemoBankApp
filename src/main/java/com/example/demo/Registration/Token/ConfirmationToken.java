/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration.Token;

import com.example.demo.AppUser.AppUser;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 *
 * @author gharibaahmedsuleiman
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
     
    @SequenceGenerator(
            name="Confirmation_token_sequence",
            sequenceName="Confirmation_token_sequence",
            allocationSize =1
        
            )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
    generator = "Confirmation_token_sequence"
    )
    
    
    private long ID;
    
    @Column(nullable=false)
    private String token;
    
     @Column(nullable=false)
    private LocalDateTime createdAt;
     
      @Column(nullable=false)
    private LocalDateTime expiresAt;
      
    private LocalDateTime confirmedAt;

    @ManyToOne //because a user can have many confirmations token until they complete it 
    @JoinColumn(nullable=false,name = "app_user_id")
    private AppUser appUser;
    
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    
        this.appUser =appUser;
    }

    @Override
    public String toString() {
        return "ConfirmationToken{" + "token=" + token + '}';
    }
    
    
            
}
