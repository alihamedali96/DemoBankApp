/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Accounts;

import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserService;
import com.example.demo.Registration.RegistrationService;
import com.example.demo.Registration.Token.ConfirmationTokenService;
import com.example.demo.TransactionHistory.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class AccountsController {
     private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
      private final AppUserService appUserService;
      private final AccountsService accountsService;
      private final TransactionService transactionService;
      
       //make  this amount a variable
          @RequestMapping(value = "/user/transfer", method = RequestMethod.POST)
	public String withdrawAccount(@ModelAttribute("account") Accounts account,@AuthenticationPrincipal AppUser loggedinUser, @RequestParam(value ="id",required =false) String accountID,@RequestParam(value ="id2",required =false) String accountID2,RedirectAttributes redirectAttributes)
	{     
            double amount =account.getBalance() ;
            long accountfrom = Long.parseLong(accountID);
            long accountto = Long.parseLong(accountID2);

            if(accountfrom==accountto){
                  String errorMessage = "Cannot Transfer to the same Account";
                  redirectAttributes.addFlashAttribute("error", errorMessage);
                  return "redirect:/transact";
            }
            
            else{
                
                if(accountsService.transfer(amount, accountfrom,loggedinUser.getId(),accountto)==false){
                        String errorMessage = "Insufficient Funds";
                        redirectAttributes.addFlashAttribute("error", errorMessage);
                        return "redirect:/transact";
                }
                else{      
                     return "redirect:/dashboard";    
                 }
            }
	}
 
        
          @RequestMapping(value = "/user/withdraw", method = RequestMethod.POST)
	public String withdrawAccount(@ModelAttribute("account") Accounts account,@AuthenticationPrincipal AppUser loggedinUser, @RequestParam(value ="id",required =false) String accountID,RedirectAttributes redirectAttributes)
	{     
            double amount =account.getBalance() ;
            long accountfrom = Long.parseLong(accountID);
                
                 if(amount ==0){
                        String errorMessage = "Withdraw Amount cannot be Empty or 0 ";
                        redirectAttributes.addFlashAttribute("error", errorMessage);
                        return "redirect:/transact";
                }
                 
                 else{
                 
                    if(accountsService.withdrawById(amount, accountfrom,loggedinUser.getId())==false){
                            String errorMessage = "Insufficient Funds";
                            redirectAttributes.addFlashAttribute("error", errorMessage);
                     //     transactionService.logTransaction(var, "Withdrawl", amount*-1, "Failed", "Insufficient Funds", LocalDateTime.now());
                            return "redirect:/transact";
                    }
                 
                    else{
                        //  transactionService.logTransaction(var, "Withdrawl", amount*-1, "Success", "Withdrawl Transaction Successful", LocalDateTime.now());
                            return "redirect:/dashboard";
                    }
                }
	}
        
            @RequestMapping(value = "/user/deposit", method = RequestMethod.POST)
	public String depositAccount(@ModelAttribute("account") Accounts account,@AuthenticationPrincipal AppUser loggedinUser, @RequestParam("id") String accountID,RedirectAttributes redirectAttributes)
	{     
            double amount =account.getBalance() ;
            long accountfrom = Long.parseLong(accountID);
            
                if(amount ==0){
                        String errorMessage = "Deposit Amount cannot be Empty or 0 ";
                        redirectAttributes.addFlashAttribute("error", errorMessage);
                        return "redirect:/transact";
                }
            
            accountsService.depositById(amount, accountfrom, loggedinUser.getId());
            return "redirect:/dashboard";
	}
        
        
       @RequestMapping(value = "/dashboard/create", method = RequestMethod.POST)
	public String dashcreateAccount(@ModelAttribute("accountsss") Accounts accountsss,@AuthenticationPrincipal AppUser loggedinUser,Model model,RedirectAttributes redirectAttributes)
	{     
            String name = accountsss.getName();
            double balance = accountsss.getBalance();
            double overdraft = accountsss.getOverdraft();
            
                if(name==""){
                        String errorMessage = "Account Name Must Be Filled";
                        redirectAttributes.addFlashAttribute("error", errorMessage);     
                        return "redirect:/dashboard";
            }
            
            accountsService.addAccount(name,balance, overdraft, loggedinUser);         
            return "redirect:/dashboard";
	}
}
