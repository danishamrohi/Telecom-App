package com.cgi.admin.service;


import com.cgi.admin.util.exception.CsrAlreadyExistsException;
import com.cgi.admin.util.exception.CsrNotFoundException;
import com.cgi.admin.model.Csr;
import com.cgi.admin.repository.CsrAdminRepository;
import com.cgi.admin.service.CsrAdminServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsrServiceTest {

    @Mock
    private CsrAdminRepository CsrRepo;
    @MockBean
    private Csr agent;
    @InjectMocks
    private CsrAdminServiceImpl CsrService;
    private List<Csr> allCsrList;
    Optional<Csr> optional;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        agent = new Csr();
        agent.setCsrId(1);
        agent.setName("Dan");
        agent.setUserName("Dan123");
        agent.setPassword("123456");
        agent.setEnabled(true);
        agent.setAdmin(false);
        allCsrList = new ArrayList<>();
        allCsrList.add(agent);
        optional = Optional.of(agent);
    }

    @Test
    public void testRegisterCsrSuccess() throws CsrAlreadyExistsException {

        Mockito.when(CsrRepo.save(agent)).thenReturn(agent);
        boolean flag = CsrService.registerCsr(agent);
        assertEquals(true, flag);

    }


    @Test
    public void testRegisterCsrFailure() {

        Mockito.when(CsrRepo.existsById(1)).thenReturn(true);
        Mockito.when(CsrRepo.save(agent)).thenReturn(agent);
        assertThrows(
        		CsrAlreadyExistsException.class,
                    () -> { CsrService.registerCsr(agent); });

    }

    @Test
    public void testFindByUserIdAndPassword() throws CsrNotFoundException {
        Mockito.when(CsrRepo.findByUserNameAndPassword("Dan123", "123456")).thenReturn(agent);
        Csr fetchedCsr = CsrRepo.findByUserNameAndPassword("Dan123", "123456");
        assertEquals(1, fetchedCsr.getCsrId());
    }
    
    @Test
    public void deleteCsrByIdTestSuccess() throws CsrNotFoundException {
        when(CsrRepo.findById(agent.getCsrId())).thenReturn(optional);
        when(CsrRepo.save(agent)).thenReturn(agent);
        boolean flag = CsrService.deleteCsr(1);
        assertThat(true, is(flag));
    }


    @Test
    public void deleteCsrByIdTestFailure() throws CsrNotFoundException {
        when(CsrRepo.findById(agent.getCsrId())).thenReturn(null);
        when(CsrRepo.save(agent)).thenReturn(agent);
        assertThrows(
        		NullPointerException.class,
                    () -> { CsrService.deleteCsr(agent.getCsrId()); });
    }


    @Test
    public void updateCsrByIdTestSuccess() throws CsrNotFoundException {
        when(CsrRepo.findById(agent.getCsrId())).thenReturn(optional);
        agent.setName("Banana");
        Boolean check = CsrService.updateCsr(agent.getCsrId(), agent);
        assertThat(check, is(true));

    }

    @Test
    public void updateCsrByIdTestFailure() throws CsrNotFoundException {
        when(CsrRepo.findById(agent.getCsrId())).thenReturn(null);
        agent.setName("Banana");
        assertThrows(
        		NullPointerException.class,
                    () -> { CsrService.updateCsr(agent.getCsrId(), agent); });
    }


    @Test
    public void getCsrByIdTestSuccess() throws CsrNotFoundException {
        when(CsrRepo.findById(1)).thenReturn(optional);
        Csr fetchedCsr = CsrService.getCsrById(1);
        assertNotNull(fetchedCsr);
        
    }

    @Test
    public void getCsrByIdTestFailure() throws CsrNotFoundException {
    	when(CsrRepo.findById(1)).thenReturn(null);
    	//Csr fetchedCsr = CsrService.getCsrById(1);
    	assertThrows(
        		NullPointerException.class,
                    () -> { CsrService.getCsrById(1); });
    }


    @Test
    public void getAllCsrTestSuccess() {
    	when(CsrRepo.findAll()).thenReturn(allCsrList);
    	List<Csr> fetchedList = CsrService.viewAllCsr();
        assertThat(fetchedList, is(allCsrList));

    }

}
