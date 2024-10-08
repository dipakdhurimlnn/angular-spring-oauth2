package com.github.vkravchenk0.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.vkravchenk0.demo.entity.Accounts;
import com.github.vkravchenk0.demo.repository.AccountsRepository;

@RestController
public class AccountController {

	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/api/myAccount")
	public Accounts getAccountDetails(@RequestParam int id) {
		Accounts accounts = accountsRepository.findByCustomerId(id);
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}
	}

}
