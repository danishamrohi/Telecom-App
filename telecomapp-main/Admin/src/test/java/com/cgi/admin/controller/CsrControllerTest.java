package com.cgi.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.cgi.admin.model.Csr;
import com.cgi.admin.service.CsrAdminService;
import com.cgi.admin.util.exception.CsrAlreadyExistsException;
import com.cgi.admin.util.exception.CsrNotFoundException;
import com.cgi.admin.controller.CsrAdminController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CsrControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CsrAdminService CsrService;
    @MockBean
    private Csr agent;

    @InjectMocks
    private CsrAdminController CsrController;


    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(CsrController).build();
        agent = new Csr();
        agent.setCsrId(1);
        agent.setName("Dan");
        agent.setUserName("Dan123");
        agent.setPassword("123456");
        agent.setEnabled(true);
        agent.setAdmin(false);
        
    }

    @Test
    public void registerUserSuccess() throws Exception {

        when(CsrService.registerCsr(any())).thenReturn(true);
        mockMvc.perform(post("/api/v1/csr/admin/register")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(agent)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void registerUserFailure() throws Exception {

        when(CsrService.registerCsr(any())).thenThrow(CsrAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/csr/admin/register")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(agent)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateUserSuccess() throws Exception {
    	agent.setName("Demi");
        when(CsrService.updateCsr(eq(agent.getCsrId()), any())).thenReturn(true);
        mockMvc.perform(put("/api/v1/csr/admin/1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(agent)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateUserFailure() throws Exception {
    	agent.setName("Demi");
    	when(CsrService.updateCsr(eq(agent.getCsrId()), any())).thenThrow(CsrNotFoundException.class);
        mockMvc.perform(put("/api/v1/csr/admin/1")
               .contentType(MediaType.APPLICATION_JSON).content(jsonToString(agent)))
               .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteUserSuccess() throws Exception {
        when(CsrService.deleteCsr(1)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/csr/admin/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteUserFailure() throws Exception {
        when(CsrService.deleteCsr(1)).thenThrow(CsrNotFoundException.class);
        mockMvc.perform(delete("/api/v1/csr/admin/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getByCsrIdSuccess() throws Exception {

        when(CsrService.getCsrById(1)).thenReturn(agent);
        mockMvc.perform(get("/api/v1/csr/admin/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByCsrIdFAilure() throws Exception {

        when(CsrService.getCsrById(1)).thenThrow(CsrNotFoundException.class);
        mockMvc.perform(get("/api/v1/csr/admin/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    

    @Test
    public void testLoginUser() throws Exception {


        String userId = "Dan123";
        String password = "123456";


        Mockito.when(CsrService.registerCsr(agent)).thenReturn(true);
        Mockito.when(CsrService.findByUserNameAndPassword(userId, password)).thenReturn(agent);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/csr/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(agent)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    // Parsing String format data into JSON format
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
