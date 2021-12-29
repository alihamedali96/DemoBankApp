/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest { 
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    
}
