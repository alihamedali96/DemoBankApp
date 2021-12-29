/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.AppUser;

import com.example.demo.Accounts.Accounts;
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
public class AppUserService implements UserDetailsService {
    
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    //loads user by email or throws exception
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
    
    
    //SIGN UP USER DB METHOD
    public String signUpUser(AppUser appUser){
        
    //checks to see if email given exists and gives error if it does because duplicate
    boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
    if(userExists){
            throw new IllegalStateException("Email already taken");
    }
    
    
    
    //encodes password for security
    String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
   // appUser.addAccount(0, 0);
    //sets encoded password  and saves it to DB
    appUser.setPassword(encodedPassword);
    userRepository.save(appUser);
    
    //send confirmation token that takes appUser as an arguement 
    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser); //15 mins after current time
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    
    //send email
    
    
    return token;
    }
    
    //enables app user (once token is accepted)        
     public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
     
   public Optional<AppUser> findById(long id){ //optional incase it finds null so program can still work
       
       return userRepository.findById(id);
   }
   
   @Transactional
   public void addAccount(double balance,double overdraft,AppUser appUser){
       appUser.getAccount();
       appUser.addAccount(balance, overdraft);
       userRepository.save(appUser);
   }
   
     
   public List<AppUser> getallUsers(){
       return userRepository.findAll();
   }
}
