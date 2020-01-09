package demo.customerservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.customerservice.model.Customer;
import demo.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
    private CustomerService customerService;

//    @GetMapping()
//    public List<Customer> getCustomers() {
//        List<Customer> customers = customerService.retrieveCustomers();
//        return customers;
//    }
    @PostMapping()
    public Customer createCustomer(@RequestBody Customer requestBody) {
    	Customer customer = customerService.createCustomer(requestBody);
    	
    	return customer;
    }
    
    
}
