package com.auth.demo.rest.model;

import java.util.Date;

import com.auth.demo.entity.AccountTransactions;

public class RestAccountTransactions {
	private String transactionId;

	private long accountNumber;

	private int customerId;

	private Date transactionDt;

	private String transactionSummary;

	private String transactionType;

	private int transactionAmt;

	private int closingBalance;

	private String createDt;

	public RestAccountTransactions(AccountTransactions accountTnx) {
		this.transactionId = accountTnx.getTransactionId();
		this.accountNumber = accountTnx.getAccountNumber();
		this.customerId = accountTnx.getCustomerId();
		this.transactionDt = accountTnx.getTransactionDt();
		this.transactionSummary = accountTnx.getTransactionSummary();
		this.transactionType = accountTnx.getTransactionType();
		this.transactionAmt = accountTnx.getTransactionAmt();
		this.closingBalance = accountTnx.getClosingBalance();
		this.createDt = accountTnx.getCreateDt();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getTransactionDt() {
		return transactionDt;
	}

	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}

	public String getTransactionSummary() {
		return transactionSummary;
	}

	public void setTransactionSummary(String transactionSummary) {
		this.transactionSummary = transactionSummary;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmt() {
		return transactionAmt;
	}

	public void setTransactionAmt(int transactionAmt) {
		this.transactionAmt = transactionAmt;
	}

	public int getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(int closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
}
