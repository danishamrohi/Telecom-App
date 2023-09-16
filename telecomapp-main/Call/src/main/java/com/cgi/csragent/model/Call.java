package com.cgi.csragent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


import java.time.LocalDate;

@Document
public class Call {

    @Id
    private String id;
    private int csrId;
    private String customerUserName;
    private int customerPhoneNumber;
    private String customerBehaviour;
    private String callDetails;
    private int callDuration;
    private int callRating;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateAdded;
    private String callSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public int getCsrId() {
        return csrId;
    }

    public String getCustomerBehaviour() {
        return customerBehaviour;
    }

    public String getCallDetails() {
        return callDetails;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public int getCallRating() {
        return callRating;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public String getCallSource() {
        return callSource;
    }

    public int getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }


    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public void setCustomerPhoneNumber(int customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCsrId(int csrId) {
        this.csrId = csrId;
    }

    public void setCustomerBehaviour(String customerBehaviour) {
        this.customerBehaviour = customerBehaviour;
    }

    public void setCallDetails(String callDetails) {
        this.callDetails = callDetails;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public void setCallRating(int callRating) {
        this.callRating = callRating;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setCallSource(String callSource) {
        this.callSource = callSource;
    }
}
