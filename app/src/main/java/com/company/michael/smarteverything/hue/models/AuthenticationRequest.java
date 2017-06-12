package com.company.michael.smarteverything.hue.models;

import com.google.gson.annotations.SerializedName;

public class AuthenticationRequest {

    @SerializedName("devicetype")
    private final String deviceType;

    public AuthenticationRequest(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
