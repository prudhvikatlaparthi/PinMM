package com.pru.pinmm.model.payloads

import com.google.gson.annotations.SerializedName

data class LoginPayload(
    @SerializedName("emailId")
    var emailId: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("loginMenuId")
    var loginMenuId: String? = null
)