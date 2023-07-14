package com.piseth.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.bank.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
