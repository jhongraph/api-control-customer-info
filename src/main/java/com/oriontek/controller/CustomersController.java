package com.oriontek.controller;

import com.oriontek.models.Address;
import com.oriontek.models.Customer;
import com.oriontek.services.users.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomersController {

    @Autowired
    private final ICustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Customer customer) {
        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return customerService.addCustomer(token, customer);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddressToCustomer(@RequestParam long id, @RequestHeader("Authorization") String authorizationHeader, @RequestBody Address address) {
        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return customerService.assignAddress(token, id, address);
    }

    @PutMapping(value = "/update", consumes = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestParam long id, @RequestBody Customer customer) {
        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return customerService.updateCustomer(token, customer, id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> allCustomer() {
        return customerService.showCustomers();
    }

    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomer(@RequestParam long id) {
        return customerService.showCustomerById(id);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<String> deleteAddress(@RequestHeader("Authorization") String authorizationHeader, @RequestParam long id) {

        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return customerService.deleteAddress(token, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String authorizationHeader, @RequestParam long id) {
        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return customerService.deleteCustomer(token, id);
    }

}
