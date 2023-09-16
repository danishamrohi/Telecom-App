package com.cgi.csragent.service;

import com.cgi.csragent.model.Call;
import com.cgi.csragent.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CallServiceImpl implements CallService{

    CallRepository callRepository;

    @Autowired
    public CallServiceImpl(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public boolean addCall(Call call) {

        Call otherCall = callRepository.save(call);

        return otherCall==null?false:true;
    }

    @Override
    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    @Override
    public List<Call> getCallsWithCustomerPhoneNumber(int phoneNumber) {
        return callRepository.findCallByCustomerPhoneNumber(phoneNumber);
    }
    @Override
    public List<Call> getCallsbyDate(LocalDate date) {
        return callRepository.findCallByDateAdded(date);
    }

    @Override
    public List<Call> getCallsbyCallSource(String callSource) {
        return callRepository.findCallByCallSource(callSource);
    }

    @Override
    public List<Call> getCallsbyCsrId(int csrId) {
        return callRepository.findCallByCsrId(csrId);
    }

    @Override
    public boolean deleteCall(String callId){
        var optCall = callRepository.findById(callId);
        if (optCall.isPresent()){
            callRepository.deleteById(callId);
            return true;
        }
        return false;
    }

}
