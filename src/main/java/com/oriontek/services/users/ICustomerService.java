package com.oriontek.services.users;

import com.oriontek.models.Address;
import com.oriontek.models.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {

    ResponseEntity<String> addCustomer(String token, Customer customer);

    ResponseEntity<String> assignAddress(String token, long id, Address address);

    ResponseEntity<String> deleteAddress(String token, long id);

    ResponseEntity<String> updateCustomer(String token, Customer customer, long id);

    ResponseEntity<String> deleteCustomer(String token, long customerId);

    ResponseEntity<List<Customer>> showCustomers();

    ResponseEntity<Customer> showCustomerById(long customerId);

}
