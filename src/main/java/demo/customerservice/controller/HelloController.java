package demo.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.customerservice.service.CustomerService;

@Controller
public class HelloController {
	@Autowired
	private CustomerService customerService;
//    @GetMapping("/hello")
//    public String hello(Model model) {
//    	model.addAttribute("customers", customerService.retrieveCustomers());
//        return "hello";
//    }
}