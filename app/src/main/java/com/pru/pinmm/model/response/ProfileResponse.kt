package com.pru.pinmm.model.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("credentials")
    val credentials: List<Credential>? = null,
    @SerializedName("sessionToken")
    val sessionToken: String? = null
)