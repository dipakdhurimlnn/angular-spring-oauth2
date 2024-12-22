package com.auth.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.demo.entity.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {

	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
