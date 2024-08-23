package com.webage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webage.dao.AuthorizationServerDao;
import com.webage.dao.ResourceServerDao;
import com.webage.domain.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired AuthorizationServerDao authorizationServiceClient;
	@Autowired ResourceServerDao resourceServerClient;

	public Iterable<Customer> findAllCustomers() {
		String jwt = authorizationServiceClient.getJwt();
		return resourceServerClient.findAllCustomers(jwt);
	}

	public Optional<Customer> findCustomerById(long id) {
		String jwt = authorizationServiceClient.getJwt();
		return resourceServerClient.findCustomerById(jwt,id);
	}

}
