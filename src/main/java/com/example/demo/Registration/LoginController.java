/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration;

import com.example.demo.Accounts.Accounts;
import com.example.demo.Accounts.AccountsService;
import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRole;
import com.example.demo.AppUser.AppUserService;
import com.example.demo.Registration.Token.ConfirmationToken;
import com.example.demo.Registration.Token.ConfirmationTokenService;
import com.example.demo.TransactionHistory.TransactionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Controller
@AllArgsConstructor
public class LoginController {
    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
      private final AppUserService appUserService;
      private final AccountsService accountsService;
      private final TransactionService transactionService;
    
    //GETS DEFAULT REGISTER PAGE  
    @RequestMapping("/") //request mapping is by default get mapping
    public String register(Model model){
        AppUser user = new AppUser();
        model.addAttribute("user", user);
        return "register";
    }
 
   
    //POST REQUEST ON REGISTER PAGE and CREATES NEW USER
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("user") AppUser user,Model model)
	{     
              //gets fields and saves user to db
            RegistrationRequest request = new RegistrationRequest(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
              
            String token = registrationService.register(request);  
            System.out.println(user);
              
              //gets confirmation link
            String fulltoken = "localhost:8080/api/v1/registration/confirm?token=" +token;
            System.out.println("Confirm registration "+fulltoken);
            ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("Token not found"));;
              
              //sends token to html
            model.addAttribute("toke", confirmationToken);
            return "myregister_success";   
	}
        
        //GETS LOGIN PAGE
        @RequestMapping("/login")
	public String mess(Model model)
	{     
            return "login";
	}
       
        //REDIRECTS LOGOUT BUTTON TO LOGIN PAGE
        @RequestMapping(value="/logout", method = RequestMethod.GET)
        public String logoutPage (HttpServletRequest request, HttpServletResponse response){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null){    
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
            return "redirect:/login?logout";
        }
        
}
