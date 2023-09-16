package com.cgi.admin.service;

import com.cgi.admin.util.exception.CsrAlreadyExistsException;
import com.cgi.admin.util.exception.CsrNotFoundException;

import java.util.List;

import com.cgi.admin.model.Csr;

public interface CsrAdminService {
	
	public Csr findByUserNameAndPassword(String username, String password) throws CsrNotFoundException;

    boolean registerCsr(Csr csragent) throws CsrAlreadyExistsException;
    
    public Csr getCsrById(int csrId) throws CsrNotFoundException;
    
    List<Csr> viewAllCsr();
    
    boolean updateCsr(int csrId, Csr csragent) throws CsrNotFoundException;
    
    boolean deleteCsr(int csrId) throws CsrNotFoundException;
}
