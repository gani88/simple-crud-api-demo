package com.enigmacamp.simplecrudapidemo.controller;

import com.enigmacamp.simplecrudapidemo.entity.Customer;
import com.enigmacamp.simplecrudapidemo.repository.CustomerRepository;
import com.enigmacamp.simplecrudapidemo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(Customer req) {
        Customer res = customerService.registerCustomer(req);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> showCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer); // Return 200 OK with the customer object
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if customer is not found
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> showAllCustomer() {
        List<Customer> res = customerService.getAllCustomers();
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<Customer> editCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        Customer preUpdate = customerService.getCustomerById(customerId);

        if (preUpdate != null) {
            preUpdate.setId(preUpdate.getId());
            Customer updated = customerService.updateCustomer(customerId, updatedCustomer);

            if (updated != null){
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomerById(@RequestParam(name = "id") String id) {
        Long longId = Long.parseLong(id);
        customerService.deleteCustomerById(longId);
        String res = "Successfully delete by id";
        return ResponseEntity.ok().body(res);
    }
}
