package com.example.food.repository;

import com.example.food.model.BankAccount;
import com.example.food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true) /*TODO repeat it*/
@Repository
public interface BankAccountCrudRepository extends JpaRepository<BankAccount,Integer> {

    @Query("SELECT b from BankAccount b  WHERE b.number=:number")
    Optional<BankAccount> findByNumber(@Param("number")Integer number);

    @Override
    @Transactional
    @SuppressWarnings("unchecked") /*TODO one more time*/
    BankAccount save(BankAccount bankAccount);


}
