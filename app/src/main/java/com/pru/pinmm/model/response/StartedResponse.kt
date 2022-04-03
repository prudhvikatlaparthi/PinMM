package com.pru.pinmm.model.response


import com.google.gson.annotations.SerializedName

data class StartedResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
)