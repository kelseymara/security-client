package com.webage.service;

import java.util.Optional;

import com.webage.domain.Customer;

public interface CustomerService {
	public Iterable<Customer> findAllCustomers();
	public Optional<Customer> findCustomerById(long id);
}
