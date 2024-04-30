package com.enigmacamp.simplecrudapidemo.services;

import com.enigmacamp.simplecrudapidemo.entity.Customer;
import com.enigmacamp.simplecrudapidemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired // kita menyerahkan semua dependency ke spring
    private CustomerRepository customerRepository;

    // Method to Register a New Customer
    public Customer registerCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    // Method to Search/Get Customer by ID
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer not found"));
    }

    // Method to Get/Show All Customer
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long customerId, Customer updateCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer toBeUpdated = optionalCustomer.get();
            toBeUpdated.setFirstName(updateCustomer.getFirstName());
            toBeUpdated.setLastName(updateCustomer.getLastName());
            toBeUpdated.setEmail(updateCustomer.getEmail());

            return customerRepository.save(toBeUpdated);
        } else {
            return null;
        }
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
