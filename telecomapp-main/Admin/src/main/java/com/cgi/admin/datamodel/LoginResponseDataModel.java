package com.cgi.admin.datamodel;

import java.util.Map;

public class LoginResponseDataModel {

    private Map<String, String> tokenMap;
    private int csrId;

    public LoginResponseDataModel() {
    }

    public LoginResponseDataModel(Map<String, String> tokenMap, int csrId) {
        this.tokenMap = tokenMap;
        this.csrId = csrId;
    }

    public Map<String, String> getTokenMap() {
        return tokenMap;
    }

    public void setTokenMap(Map<String, String> tokenMap) {
        this.tokenMap = tokenMap;
    }

    public int getCsrId() {
        return csrId;
    }

    public void setCsrId(int csrId) {
        this.csrId = csrId;
    }
}
