package com.cgi.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cgi.admin.datamodel.LoginResponseDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.admin.model.Csr;
import com.cgi.admin.service.CsrAdminService;
import com.cgi.admin.util.exception.CsrAlreadyExistsException;
import com.cgi.admin.util.exception.CsrNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1/csr")
public class CsrAdminController {
	
	private CsrAdminService csrServe;

	@Autowired
    public CsrAdminController(CsrAdminService csrServe) {
    	this.csrServe = csrServe;
	}

	@CrossOrigin
    @PostMapping("/login")
	public ResponseEntity<?> loginCsr(@RequestBody Csr csr)
	{
		try {
			Csr agent = csrServe.findByUserNameAndPassword(csr.getUserName(), csr.getPassword());
			if(!csr.isAdmin() && agent.isEnabled()) {
				String mytoken=generateToken(agent);
				HashMap mymap=new HashMap();
				mymap.put("token",mytoken);
				LoginResponseDataModel loginResponse = new LoginResponseDataModel(mymap, agent.getCsrId());
				return new ResponseEntity<>(loginResponse,HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Invalid username/password",HttpStatus.UNAUTHORIZED);
		} 
		catch (CsrNotFoundException e) {
			return new ResponseEntity<String>("Invalid username/password",HttpStatus.UNAUTHORIZED);
		}   
	}
	
	@CrossOrigin
    @PostMapping("/login/admin")
	public ResponseEntity<?> loginAdmin(@RequestBody Csr csr)
	{
		try {
				Csr agent = csrServe.findByUserNameAndPassword(csr.getUserName(), csr.getPassword());
			if(agent.isAdmin()) {
				String mytoken=generateToken(agent); 
				HashMap mymap=new HashMap();
				mymap.put("token",mytoken);
				return new ResponseEntity<HashMap>(mymap,HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Invalid username/password",HttpStatus.UNAUTHORIZED);
		} 
		catch (CsrNotFoundException e) {
			return new ResponseEntity<String>("Invalid username/password",HttpStatus.UNAUTHORIZED);
		}   
	}
	
    @CrossOrigin
    @PostMapping("/admin/register")
	public ResponseEntity<Csr> registerCsrProfile(@RequestBody Csr csragent){
		try {
			boolean check = csrServe.registerCsr(csragent);
			if(check) {
				return new ResponseEntity<Csr>(csragent, HttpStatus.CREATED);
			}
			return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
		} 
		catch(CsrAlreadyExistsException e) {
			return new ResponseEntity<Csr>(HttpStatus.CONFLICT);
		}
	}

    @CrossOrigin
	@PutMapping("/admin/{csrId}")
	public ResponseEntity<Csr> updateCsrProfile(@PathVariable("csrId") int csrId, @RequestBody Csr csragent){
		try {
			Boolean check = csrServe.updateCsr(csrId, csragent);
			if(check) {
				return new ResponseEntity<Csr>(csragent, HttpStatus.OK);
			}
		} 
		catch (CsrNotFoundException e) {
			return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
	}
	
    @CrossOrigin
	@DeleteMapping("/admin/{csrId}")
	public ResponseEntity<?> deleteCsrProfile(@PathVariable("csrId") int csrId){
		try {
			boolean isCsrProfileDeleted = csrServe.deleteCsr(csrId);
			if(isCsrProfileDeleted) {
				return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
			}
		} 
		catch (CsrNotFoundException e) {
			return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
	}

    @CrossOrigin
	@GetMapping("/admin/{csrId}")
	public ResponseEntity<Csr> getCsrProfile(@PathVariable("csrId") int csrId){
		try {
			Csr csrProfileById = csrServe.getCsrById(csrId);
			if(csrProfileById != null) {
				return new ResponseEntity<Csr>(csrProfileById, HttpStatus.OK);
			}
		} 
		catch (CsrNotFoundException e) {
			return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Csr>(HttpStatus.NOT_FOUND);
	}
	
    @CrossOrigin
	@GetMapping("/admin/getallcsr")
	public ResponseEntity<List<Csr>> getAllCsr(){
		return new ResponseEntity<List<Csr>>(csrServe.viewAllCsr(), HttpStatus.OK);
	}
    
    public String generateToken(Csr csr)
    {
    	long expiry=10_000_000;
    	
    	return Jwts.builder().setSubject(String.valueOf(csr.getCsrId())).setIssuedAt(new Date(System.currentTimeMillis()))
    											.setExpiration(new Date(System.currentTimeMillis()+ expiry ))
    											.signWith(SignatureAlgorithm.HS256, "cgicanadakey")
    											.compact();
    }
 
}


