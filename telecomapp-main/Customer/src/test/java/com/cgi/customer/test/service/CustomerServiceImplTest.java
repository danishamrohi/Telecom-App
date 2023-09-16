package com.cgi.customer.test.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cgi.customer.model.Customer;
import com.cgi.customer.repository.CustomerRepository;
import com.cgi.customer.service.CustomerServiceImpl;
import com.cgi.customer.util.exception.CustomerAlreadyExistsException;
import com.cgi.customer.util.exception.CustomerNotFoundException;


public class CustomerServiceImplTest {
	
	@Mock
	private CustomerRepository repo;
	
	@MockBean
	private Customer customer;
	
	@InjectMocks
	private CustomerServiceImpl service;
	private List<Customer> customerList;
	
	
	
	
	  @BeforeEach
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        customer = new Customer();
	        customer.setEmailId("abc@cgi.com");
	        customer.setName("User");
	        customer.setUserName("abc12");
	        customer.setPassword("abc123");
	        customer.setPhoneNumber(456945113);
	        customerList = new ArrayList<>();
	        customerList.add(customer);
	     
	    }

	    @Test
	    public void testRegisterCustomerSuccess() throws CustomerAlreadyExistsException {

	        Mockito.when(repo.save(customer)).thenReturn(customer);
	        boolean flag = service.registerCustomer(customer);
	        assertEquals(true, flag);

	    }


	    @Test
	    public void testRegisterCustomerFailure() throws CustomerAlreadyExistsException{
	    	String userName = customer.getUserName();
	    	String password = customer.getPassword();
	        Mockito.when(repo.findByUserNameAndPassword(userName, password)).thenReturn(customer);
	        Mockito.when(repo.save(customer)).thenReturn(null);
	        Exception exception = assertThrows
	        (CustomerAlreadyExistsException.class,
	                    () -> { service.registerCustomer(customer); });
	        String expectedMessage = "User Already Exists.";
	        String actualMessage = exception.getMessage();
	        assertEquals(expectedMessage, actualMessage);

	    }

	    @Test
	    public void testFindByUserNameAndPassword() throws CustomerNotFoundException {
	    	String userName = customer.getUserName();
	    	String password = customer.getPassword();
	    	Mockito.when(repo.findByUserNameAndPassword(userName, password)).thenReturn(customer);
	        Mockito.when(repo.save(customer)).thenReturn(customer);
	        Customer actualCustomer = repo.findByUserNameAndPassword(userName, password);
	        assertEquals(customer, actualCustomer);
	    }
	    
	   
	    @Test
	    public void deleteCustomerTestSuccess() throws CustomerNotFoundException {
	    	String userName = customer.getUserName();
	    	String expectedReponse = "Deleted Successfully";
	        when(repo.findById(userName)).thenReturn(Optional.of(customer));
	        String actualResponse = service.deleteCustomer(userName);
	        assertEquals(expectedReponse, actualResponse);
	    }


	    @Test
	    public void deleteCustomerTestFailure() throws CustomerNotFoundException {
	        when(repo.findById(customer.getUserName())).thenReturn(null);
	        when(repo.save(customer)).thenReturn(customer);
	        assertThrows(
	        		NullPointerException.class,
	                    () -> { service.deleteCustomer(customer.getUserName()); });
	    }


	    @Test
	    public void updateCustomerTestSuccess() throws CustomerNotFoundException {
	        when(repo.findById(customer.getUserName())).thenReturn(Optional.of(customer));
	        Customer expectedCustomer = customer;
	        expectedCustomer.setUserName("abc12");
	        when(repo.save(expectedCustomer)).thenReturn(expectedCustomer);
	        Customer actualCustomer = service.updateCustomer("abc12", customer);
	        assertEquals(actualCustomer.getUserName(),"abc12");
	        assertEquals(expectedCustomer, actualCustomer);
	    }

	    @Test
	    public void updateCustomerTestFailure() throws CustomerNotFoundException {
	        when(repo.findById(customer.getUserName())).thenReturn(Optional.empty());
	        Customer updatedCustomer = customer;
	        updatedCustomer.setUserName("abc12");
	        when(repo.save(updatedCustomer)).thenReturn(updatedCustomer);
	        Exception exception = assertThrows(
	        		CustomerNotFoundException.class,
	                    () -> { service.updateCustomer("abc12",updatedCustomer); });
	        String expectedMessage = "Customer Not found";
	        String actualMessage = exception.getMessage();
	        assertEquals(expectedMessage, actualMessage);
	       
	    }

}
