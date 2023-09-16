package com.cgi.customer.service;

import com.cgi.customer.model.Customer;
import com.cgi.customer.util.exception.CustomerAlreadyExistsException;
import com.cgi.customer.util.exception.CustomerNotFoundException;

public interface CustomerService {
	
	Customer updateCustomer(String userName ,Customer customer)throws CustomerNotFoundException;
	
	String deleteCustomer(String username) throws CustomerNotFoundException;
	
	Customer findByUserNameAndPassword(String userName, String password) throws CustomerNotFoundException;
	
    boolean registerCustomer(Customer customer) throws CustomerAlreadyExistsException;

	Customer findByPhoneNumber(int phoneNumber) throws CustomerNotFoundException;

	Customer findCustomerByUserName(String userName) throws CustomerNotFoundException;
}
