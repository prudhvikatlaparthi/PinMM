package com.pru.pinmm.remote

import com.pru.pinmm.model.payloads.LoginPayload
import com.pru.pinmm.model.payloads.MapAPIPayload
import com.pru.pinmm.model.response.LoginResponse
import com.pru.pinmm.model.response.MapResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIRepository {
    companion object {
        const val kGPSBASEURL = "http:sdfsdffssdffdf"
        const val kBASEURL = "http:sdfsdffsdf"
    }

    @POST("Login/validate")
    fun authenticateUser(@Body payload: LoginPayload): Call<LoginResponse>

    @POST("userdetails")
    fun getUserDetail(@Body payload: LoginPayload?): Call<String?>?

    @POST("loginmobile/login")
    fun getMapDetails(@Body payload: MapAPIPayload?): Call<MapResponse?>?
}