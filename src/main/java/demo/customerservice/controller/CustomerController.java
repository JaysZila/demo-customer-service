package demo.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.customerservice.model.Customer;
import demo.customerservice.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.retrieveCustomers();
        return customers;
    }
    @GetMapping("/list")
    public String getCustomersList(Model model) {
    	model.addAttribute("customers", customerService.retrieveCustomers());
        return "customer";
    }
    @PostMapping()
    public Customer createCustomer(@RequestBody Customer requestBody) {
    	Customer customer = customerService.createCustomer(requestBody);
    	
    	return customer;
    }
    
    
}
