package com.company.michael.smarteverything.hue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HueUser {

    @JsonProperty
    private String userName;

    public String getUserName() {
        return userName;
    }
}
