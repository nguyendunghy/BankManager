package com.example.bankmanager.controller.web;

import com.example.bankmanager.entity.*;
import com.example.bankmanager.entity.form.BankAccountSearchForm;
import com.example.bankmanager.lazymodel.BankAccountLazyModel;
import com.example.bankmanager.service.BankAccountService;
import com.example.bankmanager.service.SessionService;
import com.example.bankmanager.service.UserService;
import com.example.bankmanager.utils.Constants;
import com.example.bankmanager.utils.MessageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Data
@Scope(value = "session")
@Component(value = "bankAccountController")
@ELBeanName(value = "bankAccountController")
@Join(path = "/home", to = "/layout.jsf")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	private BankAccount bankAccount;

	private LoginRequest loginRequest;

	private Session session;

	private LazyDataModel<BankAccount> lazyModel;

	private BankAccountSearchForm bankAccountSearchForm;


	private static final String registerMessageId = "registerForm\\\\:messageValidate";
	private static final String loginMessageId = "loginForm\\\\:messageValidate";

	private static final String addBankAccountMessageId = "bankAccountForm\\\\:messageValidate";


	@PostConstruct
	public void init() {
		log.info("init bank account controller");
		loginRequest = new LoginRequest();
		session = null;
		bankAccountSearchForm = new BankAccountSearchForm();
		lazyModel = new BankAccountLazyModel(bankAccountService, bankAccountSearchForm);
	}



	public void prepareAddBankAccount() {
		log.info("start add bank account");
		bankAccount = new BankAccount();
	}



	public boolean addBankAccount() {
		log.info("start adding a bank account");
		if (session != null) {
			bankAccount.setCreatedBy(session.getUsername());
			bankAccount.setCreatedAt(new Date());
			String errMessage = bankAccountService.save(bankAccount);
			if (!StringUtils.isEmpty(errMessage)) {
				MessageUtils.setMessageForMessageId(addBankAccountMessageId, errMessage, "error");
				return false;
			}

			MessageUtils.setMessageForMessageId(addBankAccountMessageId, "Add bank account success", "info");
			return true;
		}
		return false;
	}

	public boolean searchBankAccount() {
		log.info("Start search bank account");
		lazyModel = new BankAccountLazyModel(bankAccountService, bankAccountSearchForm);
		log.info("bankAccountSearchForm: "+ bankAccountSearchForm);
		return false;
	}

	public boolean logout() {
		log.info("start logout");
		sessionService.remove(session);
		session = null;
		return true;
	}

}
