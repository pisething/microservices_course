package com.piseth.bank.account.service;

import java.util.List;

import com.piseth.bank.account.entity.Account;

public interface AccountService {
	Account save(Account Account);
	
	List<Account> getAccounts();
	
	Account getById(Long id);
}
