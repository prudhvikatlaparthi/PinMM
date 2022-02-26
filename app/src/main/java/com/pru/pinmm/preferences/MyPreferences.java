package com.pru.pinmm.preferences;

import android.content.SharedPreferences;

public class MyPreferences {
    private final SharedPreferences sharedPreferences;
    public MyPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private void setPref(String key, String value){
        sharedPreferences.edit().putString(key,value).apply();
    }

    private void setPref(String key, int value){
        sharedPreferences.edit().putInt(key,value).apply();
    }

    private void setPref(String key, float value){
        sharedPreferences.edit().putFloat(key,value).apply();
    }

    private String getPrefString(String key){
        return sharedPreferences.getString(key,null);
    }

    private int getPrefInt(String key){
        return sharedPreferences.getInt(key,0);
    }

    private float getPrefFloat(String key){
        return sharedPreferences.getFloat(key,0f);
    }

    public void clearMyPreferences(){
        sharedPreferences.edit().clear().apply();
    }

    //storing-------

    private final String keyLoggedUserId = "keyLoggedUserId";

    public String getKeyLoggedUserId() {
        return getPrefString(keyLoggedUserId);
    }

    public void setKeyLoggedUserId(String value) {
        setPref(keyLoggedUserId,value);
    }

    private final String keySessionToken = "keySessionToken";

    public String getKeySessionToken() {
        return getPrefString(keySessionToken);
    }

    public void setKeySessionToken(String value) {
        setPref(keySessionToken,value);
    }

    private final String keyUserId = "keyUserId";

    public int getKeyUserId() {
        return getPrefInt(keyUserId);
    }

    public void setKeyUserId(int value) {
        setPref(keyUserId,value);
    }

    private final String keyMPIN = "keyMPIN";

    public String getKeyMPIN() {
        return getPrefString(keyMPIN);
    }

    public void setKeyMPIN(String value) {
        setPref(keyMPIN,value);
    }

    private final String keyBASEURL = "keyBASEURL";

    public String getKeyBASEURL() {
        return getPrefString(keyBASEURL);
    }

    public void setKeyBASEURL(String value) {
        setPref(keyBASEURL,value);
    }
}
