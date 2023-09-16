package com.cgi.customer.test.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.cgi.customer.model.Customer;

public class CustomerTest {
	private static Customer user;

    @BeforeAll
    public static void setUp() throws  Exception {
    	user = new Customer();
    	
    	user.setName("abc");
    	user.setUserName("abc12");
    	user.setPassword("passWord");
    	user.setEmailId("abc@cgi.com");
    	user.setPhoneNumber(656445656);
    }
    @Test
    public void testBean() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Customer.class);
    }

    @Test
    public void testNameShouldNotBeEmpty() {
        assertTrue(user.getName().equalsIgnoreCase("abc"),"Name should be abc");
    }
    @Test
    public void testUserNameShouldNotBeEmpty() {
        assertTrue(user.getUserName().equalsIgnoreCase("abc12"),"UserName Should be abc12");
    }

    @Test
    public void testPasswordShouldNotBeEmpty() {
        assertTrue(user.getPassword().equalsIgnoreCase("passWord"),"Password should be passWord");
    }
    @Test
    public void testEmailIdShouldBeTrue() {
        assertTrue(user.getEmailId().equalsIgnoreCase("abc@cgi.com"),"Enabled should be true");
    }
  
}



