package com.cgi.csragent.service;

import com.cgi.csragent.model.Call;
import com.cgi.csragent.repository.CallRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class CallServiceImplTest {

    @MockBean
    private Call call;
    @Mock
    private CallRepository callRepository;
    @InjectMocks
    private CallServiceImpl callServiceImpl;
    private List<Call> callList = null;
    Optional<Call> optionalCall;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        call = new Call();
        call.setId("Call1");
        call.setCustomerPhoneNumber(1234567890);
        call.setCsrId(12);
        call.setCustomerBehaviour("Rude");
        call.setCallDetails("This was a rude call");
        call.setCallDuration(30);
        call.setCallRating(4);
        call.setDateAdded(null);
        call.setCallSource("billing");

        callList = new ArrayList<>();
        callList.add(call);
        optionalCall=Optional.of(call);
    }

    @Test
    public void createCallTestSuccess() throws Exception {
        when(callRepository.save(call)).thenReturn(call);
        boolean result = callServiceImpl.addCall(call);
        assertThat(result, is(true));
    }

    @Test
    public void createCallTestFailure() throws Exception {
        when(callRepository.save(call)).thenReturn(null);
        boolean result = callServiceImpl.addCall(call);
        assertThat(result, is(false));
    }

    @Test
    public void deleteCallSuccess() throws Exception {
        when(callRepository.findById(call.getId())).thenReturn(optionalCall);
        when(callRepository.save(call)).thenReturn(call);
        boolean flag = callServiceImpl.deleteCall("Call1");
        assertThat(true, is(flag));
    }

    @Test
    public void deleteCallFailure() throws Exception {
        when(callRepository.findById(call.getId())).thenReturn(Optional.empty());
        boolean flag = callServiceImpl.deleteCall(call.getId());
        assertThat(false, is(flag));
    }

    @Test
    public void getCallsWithCustomerPhoneNumberTestSuccess() throws Exception{
        when(callRepository.findCallByCustomerPhoneNumber(call.getCustomerPhoneNumber())).thenReturn(callList);
        var list = callServiceImpl.getCallsWithCustomerPhoneNumber(call.getCustomerPhoneNumber());
        assertThat(list.size(), is(callList.size()));
    }


    @Test
    public void getCallsWithCustomerPhoneNumberTestFailure() throws Exception{
        when(callRepository.findCallByCustomerPhoneNumber(call.getCustomerPhoneNumber())).thenReturn(new ArrayList<>());
        var list = callServiceImpl.getCallsWithCustomerPhoneNumber(call.getCustomerPhoneNumber());
        assertThat(list.size(), is(0));
    }

    @Test
    public void getCallsWithDateTestSuccess() throws Exception{
        when(callRepository.findCallByDateAdded(call.getDateAdded())).thenReturn(callList);
        var list = callServiceImpl.getCallsbyDate(call.getDateAdded());
        assertThat(list.size(), is(callList.size()));
    }


    @Test
    public void getCallsWithDateTestFailure() throws Exception{
        when(callRepository.findCallByDateAdded(call.getDateAdded())).thenReturn(new ArrayList<>());
        var list = callServiceImpl.getCallsbyDate(call.getDateAdded());
        assertThat(list.size(), is(0));
    }


    @Test
    public void getCallsWithCallSourceSuccess() throws Exception{
        when(callRepository.findCallByCallSource(call.getCallSource())).thenReturn(callList);
        var list = callServiceImpl.getCallsbyCallSource(call.getCallSource());
        assertThat(list.size(), is(callList.size()));
    }


    @Test
    public void getCallsWithCallSourceFailure() throws Exception{
        when(callRepository.findCallByCallSource(call.getCallSource())).thenReturn(new ArrayList<>());
        var list = callServiceImpl.getCallsbyCallSource(call.getCallSource());
        assertThat(list.size(), is(0));
    }


    @Test
    public void getCallsWithCsrIdSuccess() throws Exception{
        when(callRepository.findCallByCsrId(call.getCsrId())).thenReturn(callList);
        var list = callServiceImpl.getCallsbyCsrId(call.getCsrId());
        assertThat(list.size(), is(callList.size()));
    }


    @Test
    public void getCallsWithCsrIdFailure() throws Exception{
        when(callRepository.findCallByCsrId(call.getCsrId())).thenReturn(new ArrayList<>());
        var list = callServiceImpl.getCallsbyCsrId(call.getCsrId());
        assertThat(list.size(), is(0));
    }

    @Test
    public void getAllCallsSuccess() throws Exception{
        when(callRepository.findAll()).thenReturn(callList);
        var list = callServiceImpl.getAllCalls();
        assertThat(list.size(), is(callList.size()));
    }


    @Test
    public void getAllCallsFailure() throws Exception{
        when(callRepository.findAll()).thenReturn(new ArrayList<>());
        var list = callServiceImpl.getAllCalls();
        assertThat(list.size(), is(0));
    }

}
