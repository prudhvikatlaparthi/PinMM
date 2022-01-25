package com.pru.pinmm;


import com.google.gson.annotations.SerializedName;

public class LoginPayload {
    @SerializedName("emailId")
    String emailId;
    @SerializedName("password")
    String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
