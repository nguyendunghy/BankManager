package com.example.bankmanager.service;

import com.example.bankmanager.entity.LoginRequest;
import com.example.bankmanager.entity.RegisterRequest;
import com.example.bankmanager.entity.User;
import com.example.bankmanager.repository.UserRepo;
import com.example.bankmanager.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public String login(LoginRequest loginRequest){
		String message = validateLoginRequest(loginRequest);
		if(!StringUtils.isEmpty(message)){
			return message;
		}

		List<User> listUser  = userRepo.findUsersByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
		if(listUser == null || listUser.isEmpty()){
			return Constants.LOGIN.INVALID_USER_PASS;
		}

		return null;
	}

	public String validateLoginRequest(LoginRequest loginRequest){
		if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())){
			return Constants.LOGIN.USER_PASS_EMPTY;
		}
		return null;
	}

	public String register(RegisterRequest registerRequest) {
		String message = validateRegisterRequest(registerRequest);
		if (!StringUtils.isEmpty(message)) {
			return message;
		}

		List<User> listUser = userRepo.findUsersByUsername(registerRequest.getUsername());
		if (listUser != null && !listUser.isEmpty()) {
			return Constants.REGISTER.USER_EXISTED;
		}

		User user = User.builder()
				.username(registerRequest.getUsername())
				.password(registerRequest.getPassword())
				.build();
		userRepo.save(user);
		return null;
	}

	public String validateRegisterRequest(RegisterRequest registerRequest) {
		if (registerRequest == null || StringUtils.isEmpty(registerRequest.getUsername())
				|| StringUtils.isEmpty(registerRequest.getPassword())) {
			return Constants.REGISTER.USER_PASS_EMPTY;
		}

		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
			return Constants.REGISTER.CONFIRM_PASSWORD_NOT_MATCH;
		}

		return null;
	}
}
