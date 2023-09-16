package com.cgi.customer.test.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.cgi.customer.controller.CustomerController;
import com.cgi.customer.model.Customer;
import com.cgi.customer.repository.CustomerRepository;
import com.cgi.customer.service.CustomerService;
import com.cgi.customer.util.exception.CustomerAlreadyExistsException;
import com.cgi.customer.util.exception.CustomerNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



public class CustomerControllerTest {

	private MockMvc mockMvc;
	private Customer customer;
	private List<Customer> custom;

	@Mock
	CustomerService service;
	@InjectMocks
	CustomerController control;


	 @BeforeEach
	    public void setUp() throws Exception {

	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(control).build();
	        customer = new Customer();
	        customer.setName("Dan");
	        customer.setUserName("Dan123");
	        customer.setPassword("123456");
	        customer.setEmailId("abc@cgi.com");
	        customer.setPhoneNumber(445569846);
	      
	    }

	    @Test
	    public void registerUserSuccess() throws Exception {

	        when(service.registerCustomer(any())).thenReturn(true);
	        mockMvc.perform(post("/api/v1/customer/register")
	                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(customer)))
	                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	    }

	    @Test
	    public void registerUserFailure() throws Exception {

	        when(service.registerCustomer(any())).thenThrow(CustomerAlreadyExistsException.class);
	        mockMvc.perform(post("/api/v1/customer/register")
	                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(customer)))
	                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	    }

	    @Test
	    public void updateCustomerSuccess() throws Exception{
	    	String userName = customer.getUserName();
	    	when(service.updateCustomer(userName,customer)).thenReturn(customer);
			mockMvc.perform(put("/api/v1/customer/update/Dan123").contentType(MediaType.APPLICATION_JSON)
					.content(jsonToString(customer)))
					.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	    }
	    
	    
//	    @Test
//	    public void updateCustomerFailure() throws Exception{
//	    	String userName = customer.getUserName();
//	    	when(service.updateCustomer(userName,customer)).thenThrow(CustomerNotFoundException.class);
//			mockMvc.perform(put("/api/v1/customer/update/abc")
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(jsonToString(customer)))
//					.andExpect(status().isNotFound())
//					.andDo(MockMvcResultHandlers.print());
//	    }
//	    
	    
	    @Test
	    public void deleteCustomerSuccess() throws Exception {
	        when(service.deleteCustomer("Deleted")).thenReturn("Deleted");
	        mockMvc.perform(delete("/api/v1/customer/delete/Dan123")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcResultHandlers.print());

	    }


	    @Test
	    public void deleteCustomerFailure() throws Exception {
	    	when(service.deleteCustomer(anyString())).thenThrow(CustomerNotFoundException.class);
			mockMvc.perform(delete("/api/v1/customer/delete/abc")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound())
					.andDo(MockMvcResultHandlers.print());
	    }

	    @Test
	    public void testLoginUser() throws Exception {


	        String userId = "Dan123";
	        String password = "123456";


	        Mockito.when(service.registerCustomer(customer)).thenReturn(true);
	        Mockito.when(service.findByUserNameAndPassword(userId, password)).thenReturn(customer);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(customer)))
	                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	    }

	    
	    private static String jsonToString(final Object obj) throws JsonProcessingException {
	        String result;
	        try {
	        	
	        	ObjectMapper objmapper = new ObjectMapper();
	        	objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	        	objmapper.registerModule(new JavaTimeModule());
	            result = objmapper.writeValueAsString(obj);
	            
	        } catch (JsonProcessingException e) {
	            result = "Json processing error";
	        }
	        return result;
	    }
	}




