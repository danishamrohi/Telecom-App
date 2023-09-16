package com.cgi.csragent.repository;

import com.cgi.csragent.model.Call;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CallRepositoryTest {

    @Autowired
    private CallRepository callRepository;
    private Call call;

    @BeforeEach
    public void setUp(){
        call = new Call();
        call.setCustomerPhoneNumber(1234567890);
        call.setCsrId(12);
        call.setCustomerBehaviour("Rude");
        call.setCallDetails("This was a rude call");
        call.setCallDuration(30);
        call.setCallRating(4);
        call.setDateAdded(null);
        call.setCallSource("billing");
    }

    @AfterEach
    public void tearDown() throws Exception {
        callRepository.deleteAll();
    }

    @Test
    public void createCallTest() {
        callRepository.insert(call);
        Call fetchedCall = callRepository.findById(call.getId()).get();
        assertThat(4, is(fetchedCall.getCallRating()));
    }

    @Test
    public void deleteCallTest(){
        callRepository.insert(call);
        Call fetchedCall = callRepository.findById(call.getId()).get();
        assertThat(4, is(fetchedCall.getCallRating()));
        callRepository.delete(fetchedCall);
    }

    @Test
    public void getCallByPhoneNumberTest(){
        callRepository.insert(call);
        Call fetchedCall = callRepository.findById(call.getId()).get();
        assertThat(1234567890, is(fetchedCall.getCustomerPhoneNumber()));
    }

    @Test
    public void getCallByCSRIdTest(){
        callRepository.insert(call);
        Call fetchedCall = callRepository.findById(call.getId()).get();
        assertThat(12, is(fetchedCall.getCsrId()));
    }

}
