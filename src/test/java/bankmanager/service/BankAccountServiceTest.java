package bankmanager.service;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.repository.BankAccountRepo;
import com.example.bankmanager.repository.BankAccountRepoCustom;
import com.example.bankmanager.repository.UserBankAccountRepo;
import com.example.bankmanager.repository.UserRepo;
import com.example.bankmanager.service.BankAccountService;
import com.example.bankmanager.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

//@SpringBootTest
@Slf4j
public class BankAccountServiceTest {

	@Test
	public void testValidate_success() {
		BankAccountRepo bankAccountRepo = Mockito.mock(BankAccountRepo.class);
		BankAccountRepoCustom bankAccountRepoCustom = Mockito.mock(BankAccountRepoCustom.class);
		UserBankAccountRepo userBankAccountRepo = Mockito.mock(UserBankAccountRepo.class);
		UserRepo userRepo = Mockito.mock(UserRepo.class);

		BankAccountService bankAccountService = new BankAccountService(bankAccountRepo, userRepo,userBankAccountRepo,bankAccountRepoCustom);

		BankAccount bankAccount = BankAccount.builder()
				.accountNo("123456")
				.accountName("Nguyen Van Dung")
				.currency("USD")
				.createdBy("dung.kstncnttk56@gmail.com")
				.createdAt(new Date())
				.build();
		String errorMessage = bankAccountService.validate(bankAccount);
		Assert.assertNull(errorMessage);
	}

	@Test
	public void testValidate_account_no_empty_return_error_ACCOUNT_NO_EMPTY() {
		BankAccountRepo bankAccountRepo = Mockito.mock(BankAccountRepo.class);
		BankAccountRepoCustom bankAccountRepoCustom = Mockito.mock(BankAccountRepoCustom.class);
		UserBankAccountRepo userBankAccountRepo = Mockito.mock(UserBankAccountRepo.class);
		UserRepo userRepo = Mockito.mock(UserRepo.class);

		BankAccountService bankAccountService = new BankAccountService(bankAccountRepo, userRepo,userBankAccountRepo,bankAccountRepoCustom);

		BankAccount bankAccount = BankAccount.builder()
				.accountNo("")
				.accountName("Nguyen Van Dung")
				.createdBy("dung.kstncnttk56@gmail.com")
				.createdAt(new Date())
				.build();
		String errorMessage = bankAccountService.validate(bankAccount);
		Assert.assertEquals(Constants.BANK_ACCOUNT.ACCOUNT_NO_REQUIRED, errorMessage);
	}


}
