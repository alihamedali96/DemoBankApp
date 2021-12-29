/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Accounts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>  {
    
    
      @Modifying
    @Query(value ="UPDATE accounts SET balance = :new_balance WHERE id = :id AND user_id = :user_id" , nativeQuery = true)
    @Transactional
    void changeAccountBalanceById(@Param("new_balance") double new_balance, @Param("id") long id,@Param("user_id") long userid);
    
       @Modifying
    @Query(value ="UPDATE accounts SET overdraft = :new_overdraft WHERE id = :id " , nativeQuery = true)
    @Transactional
    void changeAccountOverdraftById(@Param("new_overdraft") double new_overdraft, @Param("id") long id);
    
       @Query(value = "SELECT balance FROM accounts WHERE :id = :id AND user_id = :user_id" , nativeQuery = true)
    double getBalanceById(@Param("id") long id,@Param("user_id") long userid);
    
     @Query(value = "SELECT overdraft FROM accounts WHERE :id = :id AND user_id = :user_id", nativeQuery = true)
    double getOverdraftById(@Param("id") long id,@Param("user_id") long userid);
    
    //get all accounts by user
       @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    List<Accounts> getUserAccountsById(@Param("user_id")long user_id);
    
    
    
//    @Transactional
//    void createBankAccount(@Param("ua_fk") long ua_fk,
//                           @Param("balance") double balance,
//                           @Param("overdraft") double overdraft);
}
