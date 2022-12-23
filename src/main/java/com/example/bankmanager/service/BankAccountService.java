package com.example.bankmanager.service;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.entity.User;
import com.example.bankmanager.entity.form.BankAccountSearchForm;
import com.example.bankmanager.entity.response.UserBankAccount;
import com.example.bankmanager.repository.BankAccountRepo;
import com.example.bankmanager.repository.BankAccountRepoCustom;
import com.example.bankmanager.repository.UserBankAccountRepo;
import com.example.bankmanager.repository.UserRepo;
import com.example.bankmanager.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountService {

	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserBankAccountRepo userBankAccountRepo;
	@Autowired
	private BankAccountRepoCustom bankAccountRepoCustom;


	public List<BankAccount> findAll() {
		return bankAccountRepo.findAll();
	}

	public BankAccount getById(Long id) {
		return bankAccountRepo.getOne(id);
	}

	@Transactional
	public String save(BankAccount bankAccount) {
		List<User> listUser = userRepo.findUsersByUsername(bankAccount.getCreatedBy());
		if(Objects.isNull(listUser) || listUser.size() != 1){
			return Constants.BANK_ACCOUNT.USER_NOT_FOUND;
		}

		String errMessage = validate(bankAccount);
		if (!StringUtils.isEmpty(errMessage)) {
			return errMessage;
		}


		bankAccountRepo.save(bankAccount);

		Long userId = listUser.get(0).getId();
		Long bankAccountId = bankAccount.getId();
		UserBankAccount userBankAccount = UserBankAccount.builder()
				.userId(userId)
				.bankAccountId(bankAccountId)
				.build();
		userBankAccountRepo.save(userBankAccount);

		return null;
	}

	public String validate(BankAccount bankAccount) {
		if (bankAccount == null) {
			return Constants.BANK_ACCOUNT.BANK_ACCOUNT_REQUIRED;
		}

		if (StringUtils.isEmpty(bankAccount.getAccountName())) {
			return Constants.BANK_ACCOUNT.ACCOUNT_NAME_REQUIRED;
		}

		if (StringUtils.isEmpty(bankAccount.getAccountNo())) {
			return Constants.BANK_ACCOUNT.ACCOUNT_NO_REQUIRED;
		}

		if (StringUtils.isEmpty(bankAccount.getCurrency())) {
			return Constants.BANK_ACCOUNT.CURRENCY_REQUIRED;
		}

		return null;
	}

	public List<BankAccount> findBankAccount(BankAccountSearchForm form, int page, int pageSize, String fieldOrder, String orderValue)   {
		try {
			return bankAccountRepoCustom.findBankAccount(form, page, pageSize, fieldOrder, orderValue);
		} catch (Exception e) {
			log.error("have error ", e);
		}
		return new ArrayList<>();
	}

	public int countBankAccount(BankAccountSearchForm form)   {
		try {
			return bankAccountRepoCustom.countBankAccount(form);
		} catch (Exception e) {
			log.error("have error",e);
		}
		return 0;
	}

}
