package com.cgi.customer.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cgi.customer.model.Customer;
import com.cgi.customer.service.CustomerService;
import com.cgi.customer.util.exception.CustomerAlreadyExistsException;
import com.cgi.customer.util.exception.CustomerNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	CustomerService service;
	
	@Autowired
	public CustomerController(CustomerService service) {
		this.service=service;
	}

	@CrossOrigin
	@PostMapping("/register")
	   	public ResponseEntity<?> registerUser(@RequestBody Customer user){
		 try {
			 service.registerCustomer(user);
			 return new ResponseEntity<Customer>(user, HttpStatus.CREATED);
		 } catch (CustomerAlreadyExistsException e) {
			 return new ResponseEntity<>("username already exists", HttpStatus.CONFLICT);
		 }
	 }

	@CrossOrigin
	 @PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody Customer customer) 
		{
			try {
				Customer tempcustomer = service.findByUserNameAndPassword(customer.getUserName(), customer.getPassword());
				if (tempcustomer == null) {
					return new ResponseEntity<>("Unsuccessfull, Try again!", HttpStatus.UNAUTHORIZED);
				}else {
				String mytoken=generateToken(tempcustomer); 
				HashMap<String, String> mymap=new HashMap<>();
				mymap.put("token",mytoken);
				return new ResponseEntity<>(mymap,HttpStatus.OK);
				}
			} 
			catch (CustomerNotFoundException e) {
				return new ResponseEntity<>("Invalid username/password",HttpStatus.UNAUTHORIZED);
			}   
		}

		@CrossOrigin
		@GetMapping("/getbyuser/{userName}")
		public ResponseEntity<?> getCustomerByUserName(@PathVariable("userName") String userName) 
		{
			try {
				Customer customer = service.findCustomerByUserName(userName);
				return new ResponseEntity<>(customer, HttpStatus.OK);
			} catch (CustomerNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}  
		}
	
	    public String generateToken(Customer customer)
	    {
	    	long expiry=10_000_000_00;
	    	
	    	return Jwts.builder().setSubject(String.valueOf(customer.getUserName())).setIssuedAt(new Date(System.currentTimeMillis()))
	    											.setExpiration(new Date(System.currentTimeMillis()+ expiry ))
	    											.signWith(SignatureAlgorithm.HS256, "cgicanadakey")
	    											.compact();
	    }

	@CrossOrigin
	@GetMapping("/getbyphone/{customerPhoneNumber}")
	public ResponseEntity<?> getCustomerWithPhoneNumber(@PathVariable("customerPhoneNumber") int customerPhoneNumber){
		try {
			Customer customer = service.findByPhoneNumber(customerPhoneNumber);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
   @PutMapping("/update/{userName}")
   public ResponseEntity<?> updateCustomer (@PathVariable("userName") String userName, @RequestBody Customer customer) throws CustomerNotFoundException{
	   try {
		   service.updateCustomer(userName ,customer);
		   return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	   }
	   catch(CustomerNotFoundException e) {
		   return new ResponseEntity<String>("Customer Not found",HttpStatus.NOT_FOUND);
	   }	   
   }

	@CrossOrigin
	@DeleteMapping("/delete/{userName}")
	public ResponseEntity<?> deleteCustomer(@PathVariable ("userName") String userName)throws CustomerNotFoundException{
		try {
			String response = service.deleteCustomer(userName);
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}
		catch (CustomerNotFoundException e) {
			return new ResponseEntity<String>("Customer Not found",HttpStatus.NOT_FOUND);
		}
	}

}
