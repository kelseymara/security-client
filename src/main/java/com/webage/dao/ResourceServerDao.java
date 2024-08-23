package com.webage.dao;

import java.util.Optional;

import com.webage.domain.Customer;

public interface ResourceServerDao {

    Iterable<Customer> findAllCustomers(String jwt);
    Optional<Customer> findCustomerById(String jwt, long id);

}
