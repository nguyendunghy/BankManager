package com.example.bankmanager.repository.impl;

import com.example.bankmanager.entity.BankAccount;
import com.example.bankmanager.entity.form.BankAccountSearchForm;
import com.example.bankmanager.repository.BankAccountRepoCustom;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankAccountRepoCustomImpl implements BankAccountRepoCustom {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<BankAccount> findBankAccount(BankAccountSearchForm form, int page, int pageSize, String fieldOrder, String orderValue) throws Exception {
		String sql = "SELECT id, account_name, account_no, currency, created_by, created_at " +
				"FROM bank_account " +
				"WHERE 1 = 1 ";

		List<Object> params = new ArrayList<>();
		if (!StringUtils.isEmpty(form.getAccountName())) {
			sql += "AND account_name LIKE  ? ";
			params.add("%" + form.getAccountName() + "%");
		}

		if (!StringUtils.isEmpty(form.getAccountNo())) {
			sql += "AND account_no LIKE ? ";
			params.add("%" + form.getAccountNo() + "%");
		}

		if (!StringUtils.isEmpty(form.getCurrency())) {
			sql += "AND currency LIKE ? ";
			params.add("%" + form.getCurrency() + "%");
		}

		if (page >= 0 && pageSize > 0) {
			sql += "LIMIT ? OFFSET ? ";
			params.add(pageSize);
			params.add(pageSize * page);
		}

		if (!StringUtils.isEmpty(fieldOrder) && !StringUtils.isEmpty(orderValue)) {
			sql += "ORDER BY " + fieldOrder + " " + orderValue;
		}

		Query query = entityManager.createNativeQuery(sql, BankAccount.class);
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i + 1, params.get(i));
		}

		List<BankAccount> list = query.getResultList();
		return list;
	}

	@Override
	public int countBankAccount(BankAccountSearchForm form) throws Exception {
		String sql = "SELECT count(id) " +
				"FROM bank_account " +
				"WHERE 1 = 1 ";

		List<Object> params = new ArrayList<>();
		if (!StringUtils.isEmpty(form.getAccountName())) {
			sql += "AND account_name LIKE  ? ";
			params.add("%" + form.getAccountName() + "%");
		}

		if (!StringUtils.isEmpty(form.getAccountNo())) {
			sql += "AND account_no LIKE ? ";
			params.add("%" + form.getAccountNo() + "%");
		}

		if (!StringUtils.isEmpty(form.getCurrency())) {
			sql += "AND currency LIKE ? ";
			params.add("%" + form.getCurrency() + "%");
		}


		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i + 1, params.get(i));
		}

		Long count = new Long(query.getSingleResult().toString());
		return count.intValue();
	}

}
