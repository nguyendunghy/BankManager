package com.example.bankmanager.controller.api;


import com.example.bankmanager.entity.LoginRequest;
import com.example.bankmanager.entity.RegisterRequest;
import com.example.bankmanager.entity.response.CommonResponse;
import com.example.bankmanager.service.UserService;
import com.example.bankmanager.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.example.bankmanager.utils.Constants.INTERNAL_ERROR;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public CommonResponse authentication(@RequestBody LoginRequest loginRequest) {
        log.info("Start authentication");
        String errMessage = userService.validateLoginRequest(loginRequest);
        if (!StringUtils.isEmpty(errMessage)) {
            return new CommonResponse(INTERNAL_ERROR, errMessage, null);
        }

		errMessage =  userService.login(loginRequest);
		if(!StringUtils.isEmpty(errMessage)){
			return new CommonResponse(INTERNAL_ERROR, errMessage, null);
		}

        return new CommonResponse(Constants.CODE.SUCCESS, Constants.SUCCESS);
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public CommonResponse register(@RequestBody RegisterRequest registerRequest) {
        log.info("start register");
        String errMessage = userService.register(registerRequest);
        if (!StringUtils.isEmpty(errMessage)) {
            return new CommonResponse(INTERNAL_ERROR, errMessage);
        }

        return new CommonResponse(Constants.CODE.SUCCESS, Constants.SUCCESS);
    }

    @PostMapping(value = "/logout", consumes = "application/json", produces = "application/json")
    public CommonResponse logout(@RequestHeader("token") String token) {
        log.info("start logout");

        return new CommonResponse(Constants.CODE.SUCCESS, Constants.SUCCESS);
    }
}
