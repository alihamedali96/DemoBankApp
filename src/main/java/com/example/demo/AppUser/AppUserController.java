/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.AppUser;

import com.example.demo.Accounts.Accounts;
import com.example.demo.Accounts.AccountsService;
import com.example.demo.Registration.RegistrationService;
import com.example.demo.Registration.Token.ConfirmationTokenService;
import com.example.demo.TransactionHistory.TransactionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class AppUserController {
      private final RegistrationService registrationService;
      private final ConfirmationTokenService confirmationTokenService;
      private final AppUserService appUserService;
      private final AccountsService accountsService;
      private final TransactionService transactionService;
    
    //GETS TRANSACTION PAGE
    @RequestMapping("/transact") //request mapping is by default get mapping
    public String trans(@AuthenticationPrincipal AppUser loggedinUser,Model model){
         //Gets Account Model
         Accounts account = new Accounts();
         model.addAttribute("account", account);
         //Gets all the Users Accounts
         List<Accounts> accounts = accountsService.getAccounts(loggedinUser.getId());
         model.addAttribute("accountsList", accounts); //gets user accounts  
         //Gets account ID ..delete?
         model.addAttribute("id",account.getId());
         //Gets all Bank Accounts
         List<Accounts> allAccounts = accountsService.getAllAccounts();
         model.addAttribute("allAccounts", allAccounts);
        
        return "withdrawandeposit";
    }
    
       //GETS DASHBOARD PAGE
    @RequestMapping("/dashboard") //request mapping is by default get mapping
    public String dashboard(Model model,@AuthenticationPrincipal AppUser loggedinUser ){
        //Gets the User
        model.addAttribute("user", loggedinUser);
        //Gets all the Users Accounts
        List<Accounts> accounts = accountsService.getAccounts(loggedinUser.getId());
        model.addAttribute("accountsList", accounts); //gets user accounts
        //Gets Account Model
        Accounts account = new Accounts();
        model.addAttribute("accountsss", new Accounts()); 
          
        return "dashboard";
    }
    
       //GETS ACCOUNT HISTORY PAGE
        @RequestMapping("/history")
        public String dashboard(@AuthenticationPrincipal AppUser loggedinUser ){ 
            return "history";
        }
        
        //GETS PROFILE PAGE
        @RequestMapping("/profile") 
        public String profile(@AuthenticationPrincipal AppUser loggedinUser){
            return "users-profile";
        }
}
