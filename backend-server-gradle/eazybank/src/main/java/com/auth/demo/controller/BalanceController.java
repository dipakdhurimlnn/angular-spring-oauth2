package com.auth.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.demo.entity.AccountTransactions;
import com.auth.demo.repository.AccountTransactionsRepository;
import com.auth.demo.rest.model.RestAccountTransactions;

@RestController
public class BalanceController {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@GetMapping("/api/myBalance")
	public List<RestAccountTransactions> getBalanceDetails(@RequestParam(required = false) int id) {
		System.err.println(id);
		List<AccountTransactions> accountTransactions = accountTransactionsRepository
				.findByCustomerIdOrderByTransactionDtDesc(id);
		if (accountTransactions != null) {
			return accountTransactions.stream().map(at -> new RestAccountTransactions(at)).collect(Collectors.toList());
		} else {
			return null;
		}
	}
}
