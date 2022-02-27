package com.pru.pinmm.preferences

import android.content.SharedPreferences

class MyPreferences(private val sharedPreferences: SharedPreferences) {
    private fun setPref(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun setPref(key: String, value: Int?) {
        sharedPreferences.edit().putInt(key, value ?: 0).apply()
    }

    private fun setPref(key: String, value: Float?) {
        sharedPreferences.edit().putFloat(key, value ?: 0f).apply()
    }

    private fun getPrefString(key: String): String {
        return sharedPreferences.getString(key, null) ?: ""
    }

    private fun getPrefInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    private fun getPrefFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun clearMyPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    //storing-------
    var keyLoggedUserId: String
        get() = getPrefString("keyLoggedUserId")
        set(value) = setPref("keyLoggedUserId", value)

    var keySessionToken: String
        get() = getPrefString("keySessionToken")
        set(value) = setPref("keySessionToken", value)

    var keyUserId: Int
        get() = getPrefInt("keyUserId")
        set(value) = setPref("keyUserId", value)

    var keyMPIN: String
        get() = getPrefString("keyMPIN")
        set(value) = setPref("keyMPIN", value)

    var keyBaseUrl: String
        get() = getPrefString("keyBaseUrl")
        set(value) = setPref("keyBaseUrl", value)

    var latitude: String
        get() = getPrefString("Latitude")
        set(value) = setPref("Latitude", value)

    var longitude: String
        get() = getPrefString("Longitude")
        set(value) = setPref("Longitude", value)

    var serialNumber: String
        get() = getPrefString("SERIAL_NUMBER")
        set(value) = setPref("SERIAL_NUMBER", value)

}