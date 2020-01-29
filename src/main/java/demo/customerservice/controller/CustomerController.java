package demo.customerservice.controller;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.customerservice.model.Customer;
import demo.customerservice.model.OtpRequestAndResponse;
import demo.customerservice.service.CustomerService;
import demo.customerservice.service.EmailService;
import demo.customerservice.service.OtpService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private OtpService otpService;

	@GetMapping()
	public List<Customer> getCustomers(@RequestParam(value = "hello", required = false) String hello) {
		List<Customer> customers = customerService.retrieveCustomers();
		return customers;
	}

	@GetMapping("/pages")
	public Page<Customer> getCustomersPage() {
		return customerService.retriveCustomerPage();
	}

	@GetMapping("/document")
	public Document getCustomerDoc() {
		return (Document) customerService.retrieveCustomerDoc();
	}
//	@GetMapping("/resultlist")
//	public ResultList<Customer> getCustomerResultList() {
//		return  customerService.retriveCustomerResultList();
//		
//	}

	@GetMapping("/list")
	public String getCustomersList(Model model) {
		model.addAttribute("customers", customerService.retrieveCustomers());
		return "customer";
	}

	@PostMapping()
	public void createCustomer(@RequestBody Customer requestBody) {
		customerService.createCustomer(requestBody);
	}

	@GetMapping("/header")
	public Document getHttpHeaders() {
		return customerService.fetchBooksData();
	}

//	@PutMapping("/edit")
//	public Customer editCustomerData(@RequestBody Customer requestBody) {
//		Customer customer = customerService.updateCustomerName(requestBody);
//		return customer;
//	}

	@GetMapping("/send")
	public void sendEm() {
		emailService.sendEmail();
	}

//	@GetMapping("/sendFile")
//	public String sendFile() throws MessagingException {
//
//		return emailService.sendEmailWithAttachment("otp_password");
//	}

	@GetMapping("/getOtp")
	public String getOtp(@RequestParam("key") String secret) {
		return otpService.getOtpCode(secret);

	}

	@GetMapping("/check")
	public String checkCacheKey(@RequestParam("key") String secretKey) {
		return otpService.checkAvailableKey(secretKey);

	}

	@GetMapping("/clear")
	public String clearSecret(@RequestParam("key") String secret) {
		return otpService.clearOtp(secret);
	}

	@PostMapping("/otp")
	public OtpRequestAndResponse generateOtpPassWd(@RequestParam("key") String secret) {
		return otpService.generateOtpPassword(secret);
	}

	@GetMapping("/validate")
	public boolean validateOtpCode(
			@RequestParam("input") String input,
			@RequestBody OtpRequestAndResponse body) {
		return otpService.validate(body.getKey(),input,body.getRemaining());
	}
	
	@GetMapping("/map")
	public ConcurrentMap<String, String> getCacheMap(){
		return  otpService.getConCurrentMap();
	}
}
