package com.webage.dao;

import com.webage.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ResourceServerDaoImpl implements ResourceServerDao {

    @Value("${resource.server.uri}")    String uri;
    @Value("${resource.server.port}")   String port;

    // RestClient bean, used to make HTTP requests to the resource server
    @Autowired
    RestClient client;

	private String serverUri() {
        return String.format(uri, port); // Format the URI by including the port number
	}


    // Send a GET request to the /api/customers endpoint with JWT for authorization
    @Override
    public Iterable<Customer> findAllCustomers(String jwt) {
        ResponseEntity<Customer[]> responseEntity = client
                .get()
                .uri(serverUri() + "/api/customers")
                .header("Authorization", "Bearer " + jwt) // Add Authorization header with the JWT
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Customer[].class); // Convert the response body to an array of Customer objects

        return Arrays.asList(responseEntity.getBody());
    }


    // Send a GET request to the /api/customers/{id} endpoint with JWT for authorization
    @Override
    public Optional<Customer> findCustomerById(String jwt, long id) {
        ResponseEntity<Customer> responseEntity = client
                .get()
                .uri(serverUri() + "/api/customers/{id}",id) // Set the target URI with the customer ID as a path variabl
                .header("Authorization", "Bearer " + jwt) // Add Authorization header with the JWT
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Customer.class); // Convert the response body to a Customer object

        return Optional.of(responseEntity.getBody());
    }
}
