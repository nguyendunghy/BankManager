package com.example.bankmanager.repository;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.entity.form.BankAccountSearchForm;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankAccountRepoCustom {

	List<BankAccount> findBankAccount(BankAccountSearchForm form, int page, int pageSize, String fieldOrder, String orderValue) throws Exception;

	int countBankAccount(BankAccountSearchForm form) throws Exception;
}
