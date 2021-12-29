/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Accounts;

import com.example.demo.Accounts.AccountsRepository;
import com.example.demo.AppUser.AppUser;
import com.example.demo.Registration.Token.ConfirmationToken;
import com.example.demo.Registration.Token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Service
@AllArgsConstructor
public class AccountsService implements UserDetailsService {
    
  
    private final AccountsRepository accountsRepository;
  
       public void changeAccountOverdraftById(double overdraft,long id){
         accountsRepository.changeAccountOverdraftById(overdraft, id);
     }
     
     public void depositById(double balance,long id,long userid){
         Accounts account =accountsRepository.getById(id);
         
        double currentBalance = account.getBalance(); //accountsRepository.getBalanceById(id,userid);
        double amount = balance + currentBalance;   
         accountsRepository.changeAccountBalanceById(amount, id,userid);
     }
     
         public boolean withdrawById(double amount,long id,long userid){
             
           //  double overdraft = 0.0;//accountsRepository.getOverdraftById(id,userid) *-1;
             //  double currentBalance =10.0;// accountsRepository.getBalanceById(id,userid);    Accounts account =accountsRepository.getById(id);
             
           
               Accounts account =accountsRepository.getById(id); 
              // currentBalance= account.getBalance();
              double overdraft = account.getOverdraft();
             double currentBalance= account.getBalance();
                double newBalance = currentBalance - amount;
             if(newBalance>=overdraft){ //TODO: REAL EXCEPTION HANDLING
                  
                        accountsRepository.changeAccountBalanceById(newBalance, id,userid);
                        return true;
             }
             
             else{  
                 System.out.println("Insufficient Funds");
                 return false;
             }
     }
         
           public boolean transfer(double amount,long id,long userid,long id2){
             
           //  double overdraft = 0.0;//accountsRepository.getOverdraftById(id,userid) *-1;
             //  double currentBalance =10.0;// accountsRepository.getBalanceById(id,userid);    Accounts account =accountsRepository.getById(id);
             
           
               Accounts account =accountsRepository.getById(id); 
               Accounts account2=accountsRepository.getById(id2);
               long secondAccountId = account2.getAppuser().getId();
               double currentBalance2 = account2.getBalance();
               double newBalance2 = currentBalance2 +amount;
              // currentBalance= account.getBalance();
              double overdraft = account.getOverdraft();
             double currentBalance= account.getBalance();
                double newBalance = currentBalance - amount;
             if(newBalance>=overdraft){ //TODO: REAL EXCEPTION HANDLING
                  
                        accountsRepository.changeAccountBalanceById(newBalance, id,userid);
                         accountsRepository.changeAccountBalanceById(newBalance2, id2,secondAccountId);
                         return true;
             }
             
             
             
             else{  
                 System.out.println("Insufficient Funds");
                 return false;
             }
     }
     
     
       public double getBalanceById(long id,long userid){
        return accountsRepository.getBalanceById(id,userid);
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//     public void createAccount(long userid,double balance,double overdraft){
//       accountsRepository.createBankAccount( userid,  balance,  overdraft);
//   
//     }
     
    public List<Accounts> getAccounts(long id) {
    return accountsRepository.getUserAccountsById(id);
    }
    
    public List<Accounts>getAllAccounts(){
        return accountsRepository.findAll();
    }
    
      @Transactional
   public void addAccount(String name,double balance,double overdraft,AppUser appUser){
       
       
       accountsRepository.save(new Accounts(balance,overdraft,name,appUser));
   }
    
}
