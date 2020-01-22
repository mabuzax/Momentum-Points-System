package com.momentum.points.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.momentum.points.api.repositories.CustomerRepository;
import com.momentum.points.api.repositories.PointsAccountRepository;
import com.momentum.points.api.repositories.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MomentumPointsApiApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class MomentumPointsApiApplicationTests {
	
	@Autowired  
    private CustomerRepository customerRepository;		
	
	@Autowired  
    private ProductRepository productRepository;		
	
	@Autowired  
    private PointsAccountRepository pointsRepository;
		
	@Autowired
	private MockMvc mvc;
	
    @Autowired
    WebApplicationContext webApplicationContext;
    
    @Before
    public void setup() throws Exception {
    	productRepository.deleteAll();
    	customerRepository.deleteAll();
    	pointsRepository.deleteAll();   
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @After
    public void tearDown() throws Exception {
    	productRepository.deleteAll();
    	customerRepository.deleteAll();
    	pointsRepository.deleteAll();
    }
    
    
	@Test
	void contextLoads() throws Exception {
		
	}
	
	@Test
    public void checkStartData() throws Exception {		
		assertEquals("111-1", productRepository.findByProductCode("111-1").getProductCode());  //Assert we have 1st product
        assertEquals("Mandla", customerRepository.findByCustomerId("211-1").getCustomerName()); //Assert we have 1st customer
        assertEquals(30, pointsRepository.findByCustomerId("211-1").getPointsBalance()); //Assert we have 1st points account
        assertNull(customerRepository.findByCustomerId("211-weww")); //Assert NULL if doesn't exist        
    }
	
	@Test
	@WithMockUser("user")
	public void productsList() throws Exception {
	   String uri = "/momentum/api/v1/product/list";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("Short-term Loan"));  //Random 1 of the products must exist
	   
	}
	
	
	@Test
	@WithMockUser("user")
	public void getProductByCode() throws Exception {
	   String uri = "/momentum/api/v1/product/id/111-1";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("111-1"));  //Requested ProductCode in result
	   
	}
	
	@Test
	@WithMockUser("user")
	public void getProductByWRONGCode() throws Exception {
	   String uri = "/momentum/api/v1/product/id/111-1111";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);  //NO_CONTENT
	   
	}
	
	@Test
	@WithMockUser("user")
	public void getProductByCustomerId() throws Exception {
	   String uri = "/momentum/api/v1/customer/id/211-1";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status); 
	   
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("211-1"));  //Requested CustomerID in result
	   
	}
	
	@Test
	@WithMockUser("user")
	public void getProductByWRONGCustomerId() throws Exception {
	   String uri = "/momentum/api/v1/customer/id/211-211";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);  //NO_CONTENT	  	   
	}
	
	@Test
	@WithMockUser("user")
	public void testPurchaseOneProduct() throws Exception {
	   String uri = "/momentum/api/v1/purchase?customerId=211-1&product=111-1";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status); 
	   
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("OK")); 
	}
	
	@Test
	@WithMockUser("user")
	public void testPurchaseMultipleNotEnoughPointsProducts() throws Exception {
	   String uri = "/momentum/api/v1/purchase?customerId=211-1&product=111-1&product=111-2&product=113-3";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status); 
	   
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("Customer does not have enough points")); 
	}
	
	@Test
	@WithMockUser("user")
	public void testPurchaseInvalidProductCode() throws Exception {
	   String uri = "/momentum/api/v1/purchase?customerId=211-1&product=111-1&product=111-2&product=113-1";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status); //NOT_FOUND 
	   
	   String content = mvcResult.getResponse().getContentAsString();

	   assertTrue(content, content.contains("Not Found")); 
	}
	
}
