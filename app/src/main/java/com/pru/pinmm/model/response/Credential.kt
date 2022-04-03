package com.pru.pinmm.model.response


import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phone")
    val phone: String? = null
)