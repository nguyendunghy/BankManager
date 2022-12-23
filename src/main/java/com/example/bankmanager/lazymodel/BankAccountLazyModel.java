package com.example.bankmanager.lazymodel;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.entity.form.BankAccountSearchForm;
import com.example.bankmanager.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankAccountLazyModel extends LazyDataModel<BankAccount> {
	private BankAccountService bankAccountService;
	private List<BankAccount> bankAccountList;
	private int dataSize;
	private BankAccountSearchForm bankAccountSearchForm;

	public BankAccountLazyModel(BankAccountService bankAccountService){
		this.bankAccountService = bankAccountService;
	}
	public BankAccountLazyModel(BankAccountService bankAccountService, BankAccountSearchForm bankAccountSearchForm){
		this.bankAccountService = bankAccountService;
		this.bankAccountSearchForm = bankAccountSearchForm;
	}
	@Override
	public BankAccount getRowData(String rowKey) {
		return bankAccountService.getById(Long.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(BankAccount bankAccount) {
		return bankAccount.getId();
	}

	@Override
	public List<BankAccount> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		try {
			int page = first / pageSize ;
			List<BankAccount> listBankAccount = bankAccountService.findBankAccount(bankAccountSearchForm, page, pageSize, sortField, sortOrder.toString());
			dataSize = bankAccountService.countBankAccount(bankAccountSearchForm);
			this.setRowCount(dataSize);
			return listBankAccount;
		} catch (Exception e) {
			log.error("error ", e);
		}
		return new ArrayList<>();
	}
}
