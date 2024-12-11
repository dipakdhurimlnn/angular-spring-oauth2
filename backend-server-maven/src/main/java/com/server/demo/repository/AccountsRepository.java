package com.server.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.demo.entity.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

	Accounts findByCustomerId(int customerId);

}
