/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration.Token;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    
    
    private final ConfirmationTokenRepository confirmationTokenRepository;
    
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
    
     public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
        
    }
     
    
    
     public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
