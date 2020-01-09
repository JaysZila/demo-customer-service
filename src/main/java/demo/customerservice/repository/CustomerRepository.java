package demo.customerservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.customerservice.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
   
    
}