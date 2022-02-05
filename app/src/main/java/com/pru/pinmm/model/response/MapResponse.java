package com.pru.pinmm.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapResponse {
    @SerializedName("JSON")
    private List<JsonMapResponse> jsonList;

    public List<JsonMapResponse> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<JsonMapResponse> jsonList) {
        this.jsonList = jsonList;
    }
}

