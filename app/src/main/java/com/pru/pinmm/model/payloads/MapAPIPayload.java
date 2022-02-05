package com.pru.pinmm.model.payloads;

import com.google.gson.annotations.SerializedName;

public class MapAPIPayload {
    @SerializedName("sessionToken")
    private String sessionToken;
    @SerializedName("loginName")
    private String loginName;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
