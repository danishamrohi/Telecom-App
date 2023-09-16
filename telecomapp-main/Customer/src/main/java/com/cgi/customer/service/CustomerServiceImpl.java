package com.cgi.customer.service;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cgi.customer.model.Customer;
import com.cgi.customer.repository.CustomerRepository;
import com.cgi.customer.util.exception.CustomerAlreadyExistsException;
import com.cgi.customer.util.exception.CustomerNotFoundException;

@Service
public  class CustomerServiceImpl implements CustomerService{
	
	CustomerRepository repo;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository repo) {
		this.repo=repo;
	}

	@Override
	public String deleteCustomer(String userName) throws CustomerNotFoundException {
		Optional <Customer> custom = repo.findById(userName);
		System.out.println("custom " + custom);
		if(custom.isPresent()) {
			repo.deleteById(userName);
			return "Deleted Successfully";
		}
		else {
			throw new CustomerNotFoundException("Customer Not found");
		}
		
	}

	@Override
	public Customer updateCustomer(String userName, Customer customer) throws CustomerNotFoundException {
			Optional < Customer > optionalCustomer = repo.findById(userName);
			if(optionalCustomer.isPresent()) {
				Customer updatedCustomer =repo.save(customer);
				return updatedCustomer;
			}
			else {
				throw new CustomerNotFoundException("Customer Not found");
			}
			
	}

	@Override
	public Customer findByUserNameAndPassword(String userName, String password) throws CustomerNotFoundException {
		Customer customer = repo.findByUserNameAndPassword(userName, password);
		if(customer != null)
			return customer;
		return null;
	}

	@Override
	public boolean registerCustomer(Customer customer) throws CustomerAlreadyExistsException {
		Customer customerExists = repo.findByUserNameAndPassword(customer.getUserName(), customer.getPassword());
    	if(customerExists==null) {
    		repo.save(customer);
    		return true;
    	}
    	else{
    		throw new CustomerAlreadyExistsException("User Already Exists.");
    	}
	}

	@Override
	public Customer findByPhoneNumber(int phoneNumber) throws CustomerNotFoundException {
		Customer foundCustomer = repo.findCustomerByPhoneNumber(phoneNumber);
		if (foundCustomer == null) {
			throw new CustomerNotFoundException("Customer with provided Phone Number does not exist.");
		}
		return foundCustomer;
	}

	@Override
	public Customer findCustomerByUserName(String userName) throws CustomerNotFoundException {
		Optional<Customer> foundCustomer = repo.findById(userName);
		if (foundCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with provided Phone Number does not exist.");
		}
		return foundCustomer.get();
	}

}