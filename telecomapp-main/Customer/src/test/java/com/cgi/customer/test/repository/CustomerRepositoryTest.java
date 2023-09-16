//package com.cgi.customer.test.repository;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//import java.util.List;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import org.mockito.MockitoAnnotations;
//
//import com.cgi.customer.model.Customer;
//import com.cgi.customer.repository.CustomerRepository;
//
//
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class CustomerRepositoryTest {
//
//	@Autowired
//	private CustomerRepository repo;
//	private Customer customer;
//	
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		customer = new Customer();
//		customer.setId(1);
//		customer.setEmailId("abc@cgi.com");
//		customer.setName("User");
//		customer.setPassword("passWord");
//		customer.setPhoneNumber(456599523);
//		customer.setUserName("csragent");	
//	}
//	@AfterEach
//	public void tearDown() throws Exception {
//		repo.deleteAll();
//	}
//	
//	@Test
//	public void testRegisterCustomer() {
//		repo.save(customer);
//		Customer custom = repo.findById(customer.getId()).get();
//		assertThat(customer.getId(),is(custom.getId()));
//	}
//	
//	@Test
//	public void testLoginCustomer() {
//		repo.save(customer);
//		Customer custom = repo.findById(customer.getId()).get();
//		assertThat(customer.getUserName(), is(custom.getUserName()));
//        assertThat(customer.getPassword(), is(custom.getPassword()));
//
//    }
//	
//	@Test
//    public void testUpdateCustomerSuccess() {
//    	repo.save(customer);
//    	Customer custom = repo.findById(customer.getId()).get();
//        assertThat(custom.getId(), is(custom.getId()));
//        custom.setName("User");
//        repo.save(custom);
//        custom = repo.findById(customer.getId()).get();
//        assertThat("User", is(customer.getName()));
//    }
//	
//	@Test
//	    public void testDeleteCsrSuccess() {
//	    	repo.save(customer);
//	    	Customer fetch = repo.findById(customer.getId()).get();
//	        assertThat(repo.getById(1), is(fetch.getId()));
//	    	repo.deleteById(customer.getId());
//	        assertThat(repo.findById(1).isEmpty(), is(true));
//	   
//
//	}
//	}
//
