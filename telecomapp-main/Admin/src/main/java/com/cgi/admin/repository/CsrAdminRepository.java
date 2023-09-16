package com.cgi.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.admin.model.Csr;

@Repository
public interface CsrAdminRepository extends JpaRepository<Csr,Integer>{

	public Csr findByUserNameAndPassword(String username, String password);
	
}
