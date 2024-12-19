package com.auth.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.demo.entity.Accounts;
import com.auth.demo.repository.AccountsRepository;

@RestController
public class AccountController {

	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/api/myAccount")
	public Accounts getAccountDetails(@RequestParam("id") int id) {
		System.err.println(id);
		Accounts accounts = accountsRepository.findByCustomerId(id);
		System.err.println(accounts);
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}
	}

}
