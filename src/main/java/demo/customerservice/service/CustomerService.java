package demo.customerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.lang.model.element.Element;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import demo.customerservice.model.Customer;
import demo.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

//	// MongoTemplate
//	public List<Customer> retrieveCustomers() {
//		List<Customer> customerList = new ArrayList<Customer>();
//		customerList = repository.getAllCustomers();
//		if (customerList == null || customerList.size() == 0) {
//			return customerList;
//		}
//		return customerList;
//	}
//
//	public Customer createCustomer(Customer customer) {
//		return repository.create(customer);
//	}
//
//	public Customer updateCustomerName(Customer customer) {
//		if (customer == null) {
//			return new Customer();
//		}
//		return repository.update(customer);
//	}

	public Page<Customer> retriveCustomerPage() {
		return repository.findAll(PageRequest.of(0, 2));
	}

//	public ResultList<Customer> retriveCustomerResultList(){
//		ResultList<Customer> list = new ResultList<Customer>(retrieveCustomers(),0,2,false);
//		return list;
//	}
	public List<Customer> retrieveCustomers() {

		List<Customer> customerList = new ArrayList<Customer>();
		customerList = repository.findAll();
		if (customerList == null || customerList.size() == 0) {

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
	
	public Document retrieveCustomerDoc() {
		Document docs = new Document();
		docs.append("Name", "Methaporn");
		docs.append("Surname", "Tha");
		return docs;
		
	}
	
	public Document fetchBooksData () {
		Document doc = new Document();
		HttpHeaders header = new HttpHeaders();
		doc.append("allow_method", header.getAllow());
		return doc;
	}

}
