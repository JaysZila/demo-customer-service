package demo.customerservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.customerservice.model.Customer;

@Repository
public interface CustomerRepository  extends MongoRepository<Customer,String>{
	
//	@Autowired
//	private  MongoTemplate mongoTemplate;
//	
//	public Customer create(Customer customer) {
//		mongoTemplate.save(customer, "customer");
//		return customer;
//	}
//	public List<Customer> getAllCustomers (){
//		List<Customer> customerList = mongoTemplate.findAll(Customer.class);
//		return customerList;
//	}
//	public Customer update(Customer customer) {
//		Customer customerDB = mongoTemplate.findOne
//		(Query.query(Criteria.where("id").is(customer.getId())), Customer.class);
//		mongoTemplate.save(customer, "customer");
//		return customer;
//	}
	
	public Page<Customer> findAll (Pageable pageAble) ;
	//MongoRepository
//    List<Customer> findByFirstName(String firstName) {
//    	return null;
//    }

	public List<Customer> findByFirstName(String name);
   
}
