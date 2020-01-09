package demo.customerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.customerservice.model.Customer;
import demo.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	 private CustomerRepository repository;

	    public List<Customer> retrieveCustomers() {
	    	
	    	List<Customer> customerList = new ArrayList<Customer>();
	    	customerList = repository.findAll();
	    	if(customerList == null || customerList.size() == 0) {
	    		
	    		return customerList;
	    	}
	    	
	        return customerList;
	    }

	    public Optional<Customer> retrieveCustomers(String id) {
	        return repository.findById(id);
	    }

	    public List<Customer> retrieveCustomersByName(String name) {
	        return repository.findByFirstName(name);
	    }

	    public Customer createCustomer(Customer customer) {
	        return repository.save(customer);
	    }

	    public Optional<Customer> updateCustomer(String id, Customer customer) {
	       Optional<Customer> customerOpt = repository.findById(id);
	       if (customerOpt ==null) {
	    	   return customerOpt;
	    	   
	       } else {
	    	   repository.save(customer); 
	       } 
		return customerOpt;
	      
	    }

	    public boolean deleteCustomer(String id) {
	        try {
	            repository.deleteById(id);
	            return true;
	        }
	        catch (Exception e) {
	            return false;
	        }
	    }
	    
}
