package com.pru.pinmm.remote;

import com.pru.pinmm.model.payloads.LoginPayload;
import com.pru.pinmm.model.payloads.MapAPIPayload;
import com.pru.pinmm.model.response.LoginResponse;
import com.pru.pinmm.model.response.MapResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIRepository {

    public static final String kBASEURL = "http:sdfsdffsdf";

    @POST("Login/validate")
    Call<LoginResponse> authenticateUser(@Body LoginPayload payload);

    @POST("userdetails")
    Call<String> getUserDetail(@Body LoginPayload payload);

    @POST("loginmobile/login")
    Call<MapResponse> getMapDetails(@Body MapAPIPayload payload);
}
