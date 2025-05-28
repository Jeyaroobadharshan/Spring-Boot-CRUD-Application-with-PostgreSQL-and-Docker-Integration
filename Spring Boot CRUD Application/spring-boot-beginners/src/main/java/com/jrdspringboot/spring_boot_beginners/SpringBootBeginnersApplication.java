package com.jrdspringboot.spring_boot_beginners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringBootBeginnersApplication {

	public final CustomersRepository customersRepository;

	public SpringBootBeginnersApplication(CustomersRepository customersRepository) {
		this.customersRepository = customersRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootBeginnersApplication.class, args);
	}

	@GetMapping
	public List<Customers> getCustomers(){
		return customersRepository.findAll();
	}

	record NewCustomerRequst(String name, String email, Integer age){

	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequst requst){
		Customers customers = new Customers();
		customers.setName(requst.name());
		customers.setEmail(requst.email());
		customers.setAge(requst.age());
		customersRepository.save(customers);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customersRepository.deleteById(id);
	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequst update){
		Customers existingCustomer = customersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found"));

		existingCustomer.setName(update.name());
		existingCustomer.setEmail(update.email());
		existingCustomer.setAge(update.age());

		customersRepository.save(existingCustomer);
	}

}
