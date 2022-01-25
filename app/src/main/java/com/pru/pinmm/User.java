package com.pru.pinmm;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    int userId;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("firstName")
    String firstName;
}
