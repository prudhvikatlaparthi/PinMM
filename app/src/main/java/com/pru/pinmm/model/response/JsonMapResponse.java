package com.pru.pinmm.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonMapResponse {
    @SerializedName("sessionToken")
    String sessionToken;
    @SerializedName("loginCompanyId")
    String loginCompanyId;
    @SerializedName("VehicelName")
    String VehicelName;
    @SerializedName("jobs")
    List<Job> jobList;

    @SerializedName("deliveryStatusCombo")
    List<DeliveryStatusCombo> deliveryStatusComboList;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getLoginCompanyId() {
        return loginCompanyId;
    }

    public void setLoginCompanyId(String loginCompanyId) {
        this.loginCompanyId = loginCompanyId;
    }

    public String getVehicelName() {
        return VehicelName;
    }

    public void setVehicelName(String vehicelName) {
        VehicelName = vehicelName;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<DeliveryStatusCombo> getDeliveryStatusComboList() {
        return deliveryStatusComboList;
    }

    public void setDeliveryStatusComboList(List<DeliveryStatusCombo> deliveryStatusComboList) {
        this.deliveryStatusComboList = deliveryStatusComboList;
    }
}
