/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Registration.Token;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long>{
        
    Optional<ConfirmationToken> findByToken(String Token); //jpa has a hidden filter by using the findby+fieldName method 
    //if it is an object and you want a field in that object, findby+object+fieldName
    
  
    
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
