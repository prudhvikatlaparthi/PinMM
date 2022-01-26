package com.pru.pinmm.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("sessionToken")
    String sessionToken;
    @SerializedName("user")
    User user;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
