/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.TransactionHistory;

import com.example.demo.Accounts.AccountsRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Service
@AllArgsConstructor
public class TransactionService {
    
    private final TransactRepository transactRepository;
    
    public void logTransaction(long accountid,String type,Double amount,String status,String reason,LocalDateTime date){
    
        transactRepository.logTransaction(accountid, type,amount, status, reason, date);
    }
    
}
