package bankmanager.service;

import com.example.bankmanager.entity.LoginRequest;
import com.example.bankmanager.entity.RegisterRequest;
import com.example.bankmanager.entity.User;
import com.example.bankmanager.repository.UserRepo;
import com.example.bankmanager.service.UserService;
import com.example.bankmanager.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootTest
@Slf4j
public class UserServiceTest {

	@Test
	public void testValidateLogin_success() {
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		UserService userService = new UserService(mockUserRepo);
		LoginRequest loginRequest = new LoginRequest("dungnv", "123456a@A");
		String errorMessage = userService.validateLoginRequest(loginRequest);
		Assert.assertNull(errorMessage);
	}

	@Test
	public void testValidateLogin_user_pass_empty_return_error_USER_PASS_EMPTY(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		UserService userService = new UserService(mockUserRepo);
		LoginRequest loginRequest = new LoginRequest();
		String errorMessage = userService.validateLoginRequest(loginRequest);
		Assert.assertEquals(Constants.LOGIN.USER_PASS_EMPTY,errorMessage);
	}

	@Test
	public void testLogin_success(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		List<User> list = Arrays.asList(new User());
		Mockito.when(mockUserRepo.findUsersByUsernameAndPassword(Mockito.anyString(),Mockito.anyString()))
				.thenReturn(list);

		UserService userService = new UserService(mockUserRepo);
		LoginRequest loginRequest = new LoginRequest("dung","123456aA@");

		String errorMessage = userService.login(loginRequest);
		Assert.assertNull(errorMessage);
	}

	@Test
	public void testLogin_invalid_user_return_error_INVALID_USER_PASS(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		Mockito.when(mockUserRepo.findUsersByUsernameAndPassword(Mockito.anyString(),Mockito.anyString()))
				.thenReturn(new ArrayList<>());

		UserService userService = new UserService(mockUserRepo);
		LoginRequest loginRequest = new LoginRequest("dung","123456aA@");

		String errorMessage = userService.login(loginRequest);
		Assert.assertEquals(Constants.LOGIN.INVALID_USER_PASS,errorMessage);
	}


	@Test
	public void test_validate_register_success(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		UserService userService = new UserService(mockUserRepo);
		RegisterRequest registerRequest = new RegisterRequest("dungnv", "123456a@A","123456a@A");
		String errorMessage = userService.validateRegisterRequest(registerRequest);
		Assert.assertNull(errorMessage);
	}

	@Test
	public void test_validate_register_confirmed_pass_not_match_return_error_CONFIRM_PASSWORD_NOT_MATCH(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		UserService userService = new UserService(mockUserRepo);
		RegisterRequest registerRequest = new RegisterRequest("dungnv", "123456a@A","012012a@A");
		String errorMessage = userService.validateRegisterRequest(registerRequest);
		Assert.assertEquals(Constants.REGISTER.CONFIRM_PASSWORD_NOT_MATCH,errorMessage);
	}

	@Test
	public void test_register_success() {
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		UserService userService = new UserService(mockUserRepo);
		RegisterRequest registerRequest = new RegisterRequest("dungnv", "123456a@A","123456a@A");
		String errorMessage = userService.register(registerRequest);
		Assert.assertNull(errorMessage);
	}

	@Test
	public void test_register_user_existed_return_error(){
		UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
		Mockito.when(mockUserRepo.findUsersByUsername(Mockito.anyString()))
				.thenReturn(Arrays.asList(new User()));
		UserService userService = new UserService(mockUserRepo);
		RegisterRequest registerRequest = new RegisterRequest("dungnv", "123456a@A","123456a@A");
		String errorMessage = userService.register(registerRequest);
		Assert.assertEquals(Constants.REGISTER.USER_EXISTED,errorMessage);
	}
}
