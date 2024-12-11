package com.auth.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.demo.entity.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

	Accounts findByCustomerId(int customerId);

}
