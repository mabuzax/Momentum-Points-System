package com.momentum.points.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.momentum.points.api.CustomResponse;
import com.momentum.points.api.exceptions.ContentNotFoundException;
import com.momentum.points.api.exceptions.ResourceNotFoundException;
import com.momentum.points.api.models.Customer;
import com.momentum.points.api.models.PointsAccount;
import com.momentum.points.api.models.Product;
import com.momentum.points.api.repositories.CustomerRepository;
import com.momentum.points.api.repositories.PointsAccountRepository;
import com.momentum.points.api.repositories.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Validated
@Api(value = "Momentum Shopping Points Management System", description = "Operations for Customer Points Management System")
public class PurchaseController {

	@Autowired  
    private CustomResponse response;
	
	//Inject the Customer Objects
	@Autowired  
    private CustomerRepository customerRepository;	
	
	//Inject the Products Objects
	@Autowired  
    private ProductRepository productRepository;		
	
	@Autowired  
    private PointsAccountRepository pointsRepository;
	
	
	@ApiOperation(value = "View a list of available products")
	@RequestMapping(value="/momentum/api/v1/product/list", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() throws ResourceNotFoundException, Exception {
		

		List<Product> returnedProducts = productRepository.findAll();
				
		if(returnedProducts==null) {
			throw new ResourceNotFoundException("No products found");
		}
		return new ResponseEntity<List<Product>>(returnedProducts, HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "Get product by code", response = Product.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/product/id/{code}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductByCode(@ApiParam(value = "productCode must be passed as a parameter on request", required = true) @PathVariable("code") String productCode) throws ResourceNotFoundException, Exception {
		
		
		Product returnedProduct = productRepository.findByProductCode(productCode);
		
		if(returnedProduct==null) {
			throw new ResourceNotFoundException("Could not find product with code:: " + productCode);
		}
		return new ResponseEntity<Product>(returnedProduct, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Get product by name")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/product/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByName(@ApiParam(value = "Product name must be passed as a parameter on request", required = true) @PathVariable("name") String productName) throws ResourceNotFoundException, Exception {
		
		List<Product> returnedProducts = productRepository.findAll();
		
		if(returnedProducts==null) {
			throw new ResourceNotFoundException("Could not find product by name:: " + productName);
		}
		return new ResponseEntity<List<Product>>(returnedProducts, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "View list of all customers")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/customer/list", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers() throws ResourceNotFoundException, Exception {
		
		List<Customer> returnedCustomers = customerRepository.findAll();
				
		if(returnedCustomers==null) {
			throw new ResourceNotFoundException("Could not find any customers");
		}
		return new ResponseEntity<List<Customer>>(returnedCustomers, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Get customer by customerId", response = Customer.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/customer/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(@ApiParam(value = "customerId must be supplied as a parameter on request", required = true)@PathVariable("id") String customerId) throws ResourceNotFoundException, Exception {
		
		Customer returnedCustomer = customerRepository.findByCustomerId(customerId);
		
		if(returnedCustomer==null) {
			throw new ResourceNotFoundException("Could not find customer with customerId:: " + customerId);
		}
		return new ResponseEntity<Customer>(returnedCustomer, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get customer by name")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/customer/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getCustomerByName(@ApiParam(value = "Customer name must be passed as a parameter on request", required = true) @PathVariable("name") String customerName) throws ResourceNotFoundException, Exception {
		
		List<Customer> matchingCustomers = customerRepository.findByCustomerName(customerName);
		
		if(matchingCustomers==null) {
			throw new ResourceNotFoundException("Could not find product with name:: " + customerName);
		}
		return new ResponseEntity<List<Customer>>(matchingCustomers, HttpStatus.OK);		
	}
	
	
	@ApiOperation(value = "Purchase product from shop. Will deduct customer points balance. Returns OK if successful, and Error if not")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully executed"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Forbidden access to the resource - eg. Purchase Not Allowed"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@RequestMapping(value="/momentum/api/v1/purchase", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> purchaseProducts( @ApiParam(value = "customerId must be passed as a parameter on request", required = true) @RequestParam(value="customerId", required=true) String customerId, @ApiParam(value = "Atleast 1 productId ID must be supplied. Multiple can be supplied as &product=113-1&produc1=443-1&product=444-3... etc", required = true) @RequestParam(value="product", required=true) List<String> productCodes) throws ContentNotFoundException, Exception {
		List<Product> purchasedProducts = new ArrayList<Product>();		
		
		if(customerRepository.findByCustomerId(customerId) == null) {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Customer with customerId " .concat(customerId).concat(" Not Found!"));
			
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		
		
		//Get the client available points
		PointsAccount customerPointsAccount = pointsRepository.findByCustomerId(customerId);		
		int availablePoints = customerPointsAccount.getPointsBalance();
		
		//Calculate TOTAL Required Points by going through the product IDs and get PointsCosts by ID
		int requiredPoints = 0;		
		for(String productCode : productCodes) {			
		
			if(productRepository.findByProductCode(productCode) != null) {
				requiredPoints += productRepository.findByProductCode(productCode).getPointsCost();
				purchasedProducts.add(productRepository.findByProductCode(productCode));
			} else {
					response.setStatus(HttpStatus.NOT_FOUND);
					response.setMessage("Product with productCode " .concat(productCode).concat(" Not Found!"));
					
					return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
				
		            
			}		        
		}
		
		//If client does not have points to purchase, forbid this purchase
		if(availablePoints < requiredPoints) {
			
			
			response.setStatus(HttpStatus.FORBIDDEN);
			response.setMessage("Customer does not have enough points for purchase.");
			
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		
		//If we got here, it means client has enough points, let's go on and purchase.  For our scenerio, a purchase is simply reduction of client points	
		int balancePoints = availablePoints - requiredPoints;
		customerPointsAccount.setPointsBalance(balancePoints);
		pointsRepository.save(customerPointsAccount);		
				
		response.setStatus(HttpStatus.OK);
		response.setMessage("Purchase Successful. Points Remaining: " + balancePoints );
		
		return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
	}
	
}
