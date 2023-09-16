package com.cgi.csragent.service;

import com.cgi.csragent.model.Call;

import java.time.LocalDate;
import java.util.List;

public interface CallService {

    boolean addCall(Call call);
    List<Call> getCallsWithCustomerPhoneNumber(int phoneNumber);
    List<Call> getAllCalls();
    List<Call> getCallsbyDate(LocalDate date);
    List<Call> getCallsbyCallSource(String callType);
    List<Call> getCallsbyCsrId(int csrId);
    boolean deleteCall(String callId);

}
