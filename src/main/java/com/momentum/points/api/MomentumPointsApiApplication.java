package com.momentum.points.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.momentum.points.api.models.Customer;
import com.momentum.points.api.models.PointsAccount;
import com.momentum.points.api.models.Product;
import com.momentum.points.api.repositories.CustomerRepository;
import com.momentum.points.api.repositories.PointsAccountRepository;
import com.momentum.points.api.repositories.ProductRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MomentumPointsApiApplication implements CommandLineRunner {
	//Inject the Customer Objects
	@Autowired  
    private CustomerRepository customerRepository;
	private List<Customer> customers = new ArrayList<Customer>();
	
	//Inject the Products Objects
	@Autowired  
    private ProductRepository productRepository;	
	private List<Product> products = new ArrayList<Product>();
	
	@Autowired  
    private PointsAccountRepository pointsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MomentumPointsApiApplication.class, args);
	}
	

	@Override  //Override the usual startup so we can add some startup data
    public void run(String... args) throws Exception {
		
		//Clear DB everytime the server starts.  We want new data all the time
		productRepository.deleteAll(); 
		customerRepository.deleteAll();
		pointsRepository.deleteAll();
		
		//Lets Add some dummy startup Products to the DB as per Requirement		
		products.add(new Product("111-1", "Retirement Insurance",15));
		products.add(new Product("111-2", "Short-term Loan",10));
		products.add(new Product("113-3", "Medical Aid",20));
		
		productRepository.saveAll(products);  //Save them to Mongo
		
		//Let's add a couple of dummy customers as per requirement
		customers.add(new Customer("211-1","Mandla"));				
		customers.add(new Customer("211-2", "Sipho"));
		customers.add(new Customer("211-3", "Nomsa"));
		customers.add(new Customer("211-4", "Ntombi"));
		
		customerRepository.saveAll(customers);  //Save them to Mongo
		
		//Give some dummy starting points balances to our client(s) already saved in Mongo		
		List<Customer> dbCustomers = customerRepository.findAll();		
		int accNumber=1;
		for(Customer dbCustomer : dbCustomers){			
			
			pointsRepository.save(new PointsAccount("311-" + accNumber, dbCustomer.getCustomerId(), 30));
			accNumber++;
		}
		 
	}
	
	
	
}
