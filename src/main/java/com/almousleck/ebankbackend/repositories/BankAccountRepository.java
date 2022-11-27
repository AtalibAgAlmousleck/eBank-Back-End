package com.almousleck.ebankbackend.repositories;

import com.almousleck.ebankbackend.entites.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
