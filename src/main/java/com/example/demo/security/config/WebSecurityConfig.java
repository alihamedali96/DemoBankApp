/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.security.config;

import com.example.demo.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{ //WebSecurityConfigurerAdapter has all the override methods for spring security
    
    private final AppUserService appuserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    //AUTHORIZATION
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      http.csrf().disable().authorizeRequests().antMatchers("/api/v*/registration/**").permitAll() //*current levels and **any below it
//              .anyRequest().authenticated()//.and().authorizeRequests().antMatchers("/").permitAll()
//                      .and().formLogin();//.loginPage("/login").permitAll(); //.loginpage changes the default url to mine and permit all allows everyone to access it
////      
//      http.authorizeRequests()
//      .antMatchers("/admin").hasRole("ADMIN")
//      .antMatchers("/user").hasRole("USER")
//      .antMatchers("/").permitAll().
//       and().formLogin();
      
           http.authorizeRequests()
      .antMatchers("/admin").hasRole("ADMIN")
      .antMatchers("/user").authenticated()
     .antMatchers("/dashboard").authenticated()
     .antMatchers("/transact").authenticated()
      //.antMatchers("/").permitAll().
       .and().formLogin().defaultSuccessUrl("/dashboard", true).loginPage("/login").permitAll();
    }
    
      //disable spring security
//    @Override
//    protected void configure(HttpSecurity security) throws Exception
//    {
//     security.httpBasic().disable();
//    }
    
    //AUTHENTICATION
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //authentication manager manages authentication in the app
        auth.authenticationProvider(daoAuthenticationProvider()); //the type of authentication
       //this will authenticate anyone from ..
    }
    
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ //DaoAuthenticationProvider retrieves user details from UserDetailsService
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder); //bCrypt is the spring-recommended password encoder
    provider.setUserDetailsService(appuserService);
    return provider;
    }
}
