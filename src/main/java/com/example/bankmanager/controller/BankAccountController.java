package com.example.bankmanager.controller;

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
@Join(path = "/", to = "/layout.jsf")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	private BankAccount bankAccount;

	private RegisterRequest registerRequest;

	private LoginRequest loginRequest;

	private Session session;

	private LazyDataModel<BankAccount> lazyModel;

	private BankAccountSearchForm bankAccountSearchForm;


	private static final String registerMessageId = "registerForm\\\\:messageValidate";
	private static final String loginMessageId = "loginForm\\\\:messageValidate";

	private static final String addBankAccountMessageId = "bankAccountForm\\\\:messageValidate";


	@PostConstruct
	public void init() {
		log.info("init video controller");
		registerRequest = new RegisterRequest();
		loginRequest = new LoginRequest();
		session = null;
		bankAccountSearchForm = new BankAccountSearchForm();
		lazyModel = new BankAccountLazyModel(bankAccountService, bankAccountSearchForm);
	}

	public void prepareLogin() {
		log.info("start prepare login");
		loginRequest = new LoginRequest();
	}

	public void prepareRegister() {
		log.info("start prepare register");
		registerRequest = new RegisterRequest();
	}

	public void prepareAddBankAccount() {
		log.info("start add bank account");
		bankAccount = new BankAccount();
	}


	public boolean login() {
		log.info("Start login");
		String errMessage = userService.login(loginRequest);
		if (!StringUtils.isEmpty(errMessage)) {
			MessageUtils.setMessageForMessageId(loginMessageId, errMessage, "error");
			return false;
		}

		session = Session.builder()
				.session(UUID.randomUUID().toString())
				.username(loginRequest.getUsername())
				.expiredTime(System.currentTimeMillis() + Constants.EXPIRED_TIME)
				.status(Constants.ACTIVE)
				.build();
		sessionService.save(session);

		MessageUtils.setMessageForMessageId(loginMessageId, "Login success", "info");
		return true;
	}

	public boolean register() {
		log.info("start register");
		String errMessage = userService.register(registerRequest);
		if (!StringUtils.isEmpty(errMessage)) {
			MessageUtils.setMessageForMessageId(registerMessageId, errMessage, "error");
			return false;
		}

		MessageUtils.setMessageForMessageId(registerMessageId, "Register success", "info");
		return true;
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
