/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration;

/**
 *
 * @author gharibaahmedsuleiman
 */

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class EmailValidator implements Predicate<String>{

    @Override
    public boolean test(String t) {
        //TODO regex to validate email
        return true;
    }
    
}
