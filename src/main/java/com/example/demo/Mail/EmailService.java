/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    
    @Override
    @Async  
    public void send(String to, String email) {
     try{
     
     MimeMessage mimeMessage = mailSender.createMimeMessage();
     MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
     helper.setText(email,true); //true = html
     helper.setTo(to);
     helper.setSubject("Confirm your email");
     helper.setFrom("info@pepisandbox.com");
     mailSender.send(mimeMessage);
        
    } catch(MessagingException e){
        LOGGER.error("Failed to send email");
        throw new IllegalStateException("Failed to send email");
    }
    
}

}
