package com.example.bankmanager.controller.web;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.entity.LoginRequest;
import com.example.bankmanager.entity.RegisterRequest;
import com.example.bankmanager.entity.Session;
import com.example.bankmanager.service.SessionService;
import com.example.bankmanager.service.UserService;
import com.example.bankmanager.utils.Constants;
import com.example.bankmanager.utils.MessageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Data
@Scope(value = "session")
@Component(value = "loginController")
@ELBeanName(value = "loginController")
@Join(path = "/login", to = "/login.jsf")
public class LoginController {
	private static final String HOME_PAGE = "home";

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	private BankAccount bankAccount;

	private RegisterRequest registerRequest;

	private LoginRequest loginRequest;

	private Session session;


	private static final String loginMessageId = "loginForm\\\\:messageValidate";


	private static final String registerMessageId = "registerForm\\\\:messageValidate";

	@PostConstruct
	public void init() {
		log.info("init bank account controller");
		registerRequest = new RegisterRequest();
		loginRequest = new LoginRequest();
		session = null;
	}

	public void prepareLogin() {
		log.info("start prepare login");
		loginRequest = new LoginRequest();
	}
	public void prepareRegister() {
		log.info("start prepare register");
		registerRequest = new RegisterRequest();

//		RequestContext requestContext = RequestContext.getCurrentInstance();
//		requestContext.execute("openModalAction('#modalRegister')");
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
	public String login() throws IOException {
		log.info("Start login");
		String errMessage = userService.login(loginRequest);
		if (!StringUtils.isEmpty(errMessage)) {
			MessageUtils.setMessageForMessageId(loginMessageId, errMessage, "error");
			return null;
		}

		session = Session.builder()
				.session(UUID.randomUUID().toString())
				.username(loginRequest.getUsername())
				.expiredTime(System.currentTimeMillis() + Constants.EXPIRED_TIME)
				.status(Constants.ACTIVE)
				.build();
		sessionService.save(session);

		FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
		return HOME_PAGE;
	}



}
