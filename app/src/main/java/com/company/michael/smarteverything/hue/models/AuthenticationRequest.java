package com.company.michael.smarteverything.hue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequest {

    @JsonProperty
    private final String deviceType;

    public AuthenticationRequest(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
