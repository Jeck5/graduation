package com.example.bankaccount.repository;

import com.example.bankaccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true) /*TODO repeat it*/
@Repository
public interface BankAccountCrudRepository extends JpaRepository<BankAccount,Integer> {

    @Query("SELECT b from BankAccount b  WHERE b.id=:id")
    Optional<BankAccount> findById(@Param("id")Integer id);

    @Override
    @Transactional
    @SuppressWarnings("unchecked") /*TODO one more time*/
    BankAccount save(BankAccount bankAccount);


}
