package com.cgi.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cgi.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
	Customer findByUserNameAndPassword(String userName, String password);
	Customer findCustomerByPhoneNumber(int phoneNumber);
	Customer findCustomerByUserName(String userName);
}
