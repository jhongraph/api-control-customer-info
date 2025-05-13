package com.oriontek.services.users;


import com.oriontek.models.Address;
import com.oriontek.models.Customer;
import com.oriontek.models.User;
import com.oriontek.repository.AddressRepository;
import com.oriontek.repository.CustomerRepository;
import com.oriontek.repository.UserRepository;
import com.oriontek.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public ResponseEntity<String> addCustomer(String token, Customer customer) {

        User user = userRepository.findByUsername(jwtUtils.extractUsername(token));

        if (user == null) return ResponseEntity.badRequest().body("invalid token");

        if (customer.getAddresses() != null) {
            customer.getAddresses().forEach(address -> {
                address.setCustomer(customer);
            });
        }

        customerRepository.save(customer);


        return ResponseEntity.ok("customer added successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> assignAddress(String token, long id, Address address) {
        User user = userRepository.findByUsername(jwtUtils.extractUsername(token));
        if (user == null) return ResponseEntity.badRequest().body("invalid token");

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer don't fount"));

        address.setCustomer(customer);

        log.error(address.getCity());

        addressRepository.save(address);

        return ResponseEntity.ok("Address added successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteAddress(String token, long id) {
        User user = userRepository.findByUsername(jwtUtils.extractUsername(token));
        if (user == null) return ResponseEntity.badRequest().body("INVALID TOKEN");

        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("ADDRESS DON'T FOUND"));

        Customer customer = address.getCustomer();
        if (customer != null) {
            customer.getAddresses().remove(address);
        }

        addressRepository.delete(address);

        return ResponseEntity.ok("Address deleted successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateCustomer(String token, Customer customers, long id) {

        User user = userRepository.findByUsername(jwtUtils.extractUsername(token));
        if (user == null) return ResponseEntity.badRequest().body("invalid token");

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer don't fount"));

        if (customers.getFirstName() != null) customer.setFirstName(customers.getFirstName());
        if (customers.getLastName() != null) customer.setLastName(customers.getLastName());

        customerRepository.save(customer);

        return ResponseEntity.ok("customer update successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCustomer(String token, long id) {
        User user = userRepository.findByUsername(jwtUtils.extractUsername(token));
        if (user == null) return ResponseEntity.badRequest().body("invalid token");

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer don't fount"));

        customerRepository.delete(customer);

        return ResponseEntity.ok("costumer deleted successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<List<Customer>> showCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @Override
    @Transactional
    public ResponseEntity<Customer> showCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer don't fount"));
        return ResponseEntity.ok(customer);
    }
}
