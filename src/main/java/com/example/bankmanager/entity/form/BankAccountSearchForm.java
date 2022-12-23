package com.example.bankmanager.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccountSearchForm {

	private String accountName ;

	private String accountNo ;

	private String currency ;

}
