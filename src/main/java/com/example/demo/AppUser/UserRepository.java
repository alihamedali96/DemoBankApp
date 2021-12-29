/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.AppUser;

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
@Repository //repositories are usually interfaces because most functions of a repository in an application are the same and will be provided
@Transactional(readOnly = true) 
public interface UserRepository extends JpaRepository<AppUser, Long> { //the repository needs the entity and its id type
    Optional<AppUser> findByEmail(String email);
    
     @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1") 
    int enableAppUser(String email); //updates user to enabled ..int because boolean is 0 when false and 1 when true
    
  
    
}
