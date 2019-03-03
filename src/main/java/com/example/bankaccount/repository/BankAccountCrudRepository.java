package com.example.bankaccount.repository;

import com.example.bankaccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface BankAccountCrudRepository extends JpaRepository<BankAccount,Integer> {

    @Query("SELECT b from BankAccount b  WHERE b.accountNumber=:accountNumber")
    Optional<BankAccount> findByAccountNumber(@Param("accountNumber")Integer accountNumber);

    @Override
    @SuppressWarnings("unchecked")
    BankAccount save(BankAccount bankAccount);
}
