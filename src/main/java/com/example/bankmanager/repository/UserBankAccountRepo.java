package com.example.bankmanager.repository;

import com.example.bankmanager.entity.response.UserBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBankAccountRepo extends JpaRepository<UserBankAccount,Long> {
}
