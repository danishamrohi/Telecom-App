package com.cgi.admin.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cgi.admin.model.Csr;

import org.mockito.MockitoAnnotations;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CsrRepositoryTest {

    
    @Autowired
    private CsrAdminRepository CsrRepo;
    
    private Csr agent;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        agent = new Csr();
        agent.setCsrId(1);
        agent.setName("Ainz");
    	agent.setUserName("Dan");
    	agent.setPassword("pass");
    	agent.setEnabled(true);
    	agent.setAdmin(false);
    }

    @AfterEach
    public void tearDown() throws Exception {
    	CsrRepo.deleteAll();
    }


    @Test
    public void testRegisterCsrSuccess() {
    	CsrRepo.save(agent);
    	Csr fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
        assertThat(agent.getCsrId(), is(fetchCsr.getCsrId()));
    }

    @Test
    public void testLoginCsrSuccess() {
    	CsrRepo.save(agent);
    	Csr fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
        assertThat(agent.getUserName(), is(fetchCsr.getUserName()));
        assertThat(agent.getPassword(), is(fetchCsr.getPassword()));
    }
    
    @Test
    public void testDeleteCsrSuccess() {
    	CsrRepo.save(agent);
    	Csr fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
        assertThat(agent.getCsrId(), is(fetchCsr.getCsrId()));
    	CsrRepo.deleteById(agent.getCsrId());
        assertThat(CsrRepo.findById(1).isEmpty(), is(true));
    }

    @Test
    public void testUpdateCsrSuccess() {
    	CsrRepo.save(agent);
    	Csr fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
        assertThat(agent.getCsrId(), is(fetchCsr.getCsrId()));
        fetchCsr.setName("Banana");
        CsrRepo.save(fetchCsr);
        fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
        assertThat("Banana", is(fetchCsr.getName()));
    }

    @Test
    public void testCsrByIdSuccess() {
    	CsrRepo.save(agent);
    	Csr fetchCsr = CsrRepo.findById(agent.getCsrId()).get();
    	assertThat(1, is(fetchCsr.getCsrId()));
    }
    
    @Test
    public void testGetAllCsrSuccess() {
    	CsrRepo.save(agent);
    	List<Csr> templist = CsrRepo.findAll();
    	assertThat(templist, is(CsrRepo.findAll()));
    }

}