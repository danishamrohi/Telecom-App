package com.cgi.csragent.controller;

import com.cgi.csragent.model.Call;
import com.cgi.csragent.service.CallService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CallControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MockMvc mockMvc;
    private Call call;
    @MockBean
    private CallService callService;
    @InjectMocks
    private CallController callController;
    private List<Call> callList = null;


    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callController).build();
        call = new Call();
        call.setId("Call1");
        call.setCustomerPhoneNumber(1234567890);
        call.setCsrId(12);
        call.setCustomerBehaviour("Rude");
        call.setCallDetails("This was a rude call");
        call.setCallDuration(30);
        call.setCallRating(4);
        call.setDateAdded(LocalDate.parse("2021-02-15"));
        call.setCallSource("billing");

        callList = new ArrayList<>();
        callList.add(call);

    }


    @Test
    public void createCallSuccess() throws Exception{
        when(callService.addCall(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/call/add")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(call)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createCallFailure() throws Exception{
        when(callService.addCall(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/call/add")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(call)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCallSuccess() throws Exception {

        when(callService.deleteCall("Call1")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/call/delete/Call1")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCallFailure() throws Exception {
        when(callService.deleteCall("Call1")).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/call/delete/Call1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCallsByCustomerPhoneNumberSuccess() throws Exception {
        when(callService.getCallsWithCustomerPhoneNumber(call.getCustomerPhoneNumber())).thenReturn(callList);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-ph/1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getCallsByCustomerPhoneNumberFailure() throws Exception {
        when(callService.getCallsWithCustomerPhoneNumber(call.getCustomerPhoneNumber())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-ph/1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getCallsByDateSuccess() throws Exception {
        when(callService.getCallsbyDate(call.getDateAdded())).thenReturn(callList);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-date/2021-02-15")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getCallsByDateFailure() throws Exception {
        when(callService.getCallsbyDate(call.getDateAdded())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-date/2021-02-15")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void getCallsByCallSourceSuccess() throws Exception {
        when(callService.getCallsbyCallSource(call.getCallSource())).thenReturn(callList);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-source/billing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getCallsByCallSourceFailure() throws Exception {
        when(callService.getCallsbyCallSource(call.getCallSource())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-source/billing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getCallsByCsrIdSuccess() throws Exception {
        when(callService.getCallsbyCsrId(call.getCsrId())).thenReturn(callList);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-csrid/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getCallsByCsrIdFailure() throws Exception {
        when(callService.getCallsbyCsrId(call.getCsrId())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/call/all-with-csrid/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objmapper = new ObjectMapper();
            objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
