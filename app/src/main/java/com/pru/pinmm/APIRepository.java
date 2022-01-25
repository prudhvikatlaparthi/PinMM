package com.pru.pinmm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIRepository {

    public static final String kBASEURL = "http:sdfsdffsdf";

    @POST("Login/validate")
    Call<LoginResponse> authenticateUser(@Body LoginPayload payload);

    @POST("userdetails")
    Call<String> getUserDetail(@Body LoginPayload payload);
}
