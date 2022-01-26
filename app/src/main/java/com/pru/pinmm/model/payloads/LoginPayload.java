package com.pru.pinmm.model.payloads;


import com.google.gson.annotations.SerializedName;

public class LoginPayload {
    @SerializedName("emailId")
    String emailId;
    @SerializedName("password")
    String password;
    @SerializedName("loginMenuId")
    String loginMenuId;

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

    public String getLoginMenuId() {
        return loginMenuId;
    }

    public void setLoginMenuId(String loginMenuId) {
        this.loginMenuId = loginMenuId;
    }
}
