package com.piseth.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.bank.account.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
