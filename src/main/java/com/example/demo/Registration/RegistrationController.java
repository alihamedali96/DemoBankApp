/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration;

import com.example.demo.AppUser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author gharibaahmedsuleiman
 */
@RestController //makes this a restful app
@RequestMapping(path="api/v1/registration") //http link
@AllArgsConstructor
public class RegistrationController {
    
    private final RegistrationService registrationService; //
    
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){ //registrationrequest is an object with the variables needed
        
            return registrationService.register(request); //runs register method 
    }
    
    @GetMapping (path = "confirm") //after confirm keyword, token code for full confirmation code, will be a get request
    public RedirectView confirm (@RequestParam("token") String token){ //the request param token is whats put into string token 
     registrationService.confirmToken(token);
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://springdemo96.herokuapp.com/login");
    return redirectView;
    
   
    }
    
    
}
