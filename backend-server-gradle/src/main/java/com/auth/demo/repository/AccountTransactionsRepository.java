package com.auth.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.demo.entity.AccountTransactions;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {

	List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
