package com.cgi.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.admin.model.Csr;
import com.cgi.admin.repository.CsrAdminRepository;
import com.cgi.admin.util.exception.CsrAlreadyExistsException;
import com.cgi.admin.util.exception.CsrNotFoundException;

@Service
public class CsrAdminServiceImpl implements CsrAdminService{

	private CsrAdminRepository csrRepo;
	@Autowired
	public CsrAdminServiceImpl(CsrAdminRepository csrRepo) {
		super();
		this.csrRepo = csrRepo;
	}
	
	@Override
	public Csr findByUserNameAndPassword(String username, String password) throws CsrNotFoundException {
		Csr agent = csrRepo.findByUserNameAndPassword(username, password);
		if(agent != null)
			return agent;
		return null;
	}

	@Override
	public boolean registerCsr(Csr csragent) throws CsrAlreadyExistsException {
		Boolean csrExists = csrRepo.existsById(csragent.getCsrId());
    	if(csrExists == false) {
    		Csr csrProfile = csrRepo.save(csragent);
    		if(csrProfile != null)
    			return true;
    	}
        throw new CsrAlreadyExistsException("CSR Profile already exists");

	}

	@Override
    public Csr getCsrById(int csrId) throws CsrNotFoundException {
		Csr csrProfileById = csrRepo.findById(csrId).get();
    	if(csrProfileById != null) {
    		return csrProfileById;
    	}
        throw new CsrNotFoundException("CSR Profile not found");
    }
	
	@Override
	public List<Csr> viewAllCsr() {
		return csrRepo.findAll();
	}

	@Override
	public boolean updateCsr(int csrId, Csr csragent) throws CsrNotFoundException {
		Csr csrProfileById = csrRepo.findById(csrId).get();
    	if(csrProfileById != null) {
    		csrRepo.save(csragent);
    		return true;
    	}
        throw new CsrNotFoundException("CSR Profile not found");

	}

	@Override
	public boolean deleteCsr(int csrId) throws CsrNotFoundException {
		Csr csrProfileById = csrRepo.findById(csrId).get();
    	if(csrProfileById != null) {
    		csrRepo.deleteById(csrId);
    		return true;
    	}
    	throw new CsrNotFoundException("CSR Profile not found");

	}

}
