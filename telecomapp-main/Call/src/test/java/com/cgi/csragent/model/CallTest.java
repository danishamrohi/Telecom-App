package com.cgi.csragent.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallTest {

    private static Call call;

    @BeforeAll
    public static void setUp() throws Exception{
        call = new Call();
        call.setId("Call1");
        call.setCustomerPhoneNumber(1234567890);
        call.setCsrId(12);
        call.setCustomerBehaviour("Rude");
        call.setCallDetails("This was a rude call");
        call.setCallDuration(30);
        call.setCallRating(4);
        call.setDateAdded(LocalDate.parse("2021-02-15", DateTimeFormatter.ISO_LOCAL_DATE));
        call.setCallSource("billing");
    }

    @Test
    public void testCallIdShouldNotBeEmpty() {
        assertTrue(call.getId().equals("Call1"));
    }

    @Test
    public void testCustomerIdShouldNotBeEmpty() {
        assertTrue(call.getCallRating()==4);
    }

    @Test
    public void testPhoneNumberShouldNotBeEmpty() {
        assertTrue(call.getCustomerPhoneNumber()==1234567890);
    }

    @Test
    public void testCsrIdShouldNotBeEmpty() {
        assertTrue(call.getCsrId()==12);
    }

    @Test
    public void testCallDurationShouldNotBeEmpty() {
        assertTrue(call.getCallDuration()==30);
    }

}
