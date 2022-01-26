package com.pru.pinmm.model.response;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    int userId;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("firstName")
    String firstName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
