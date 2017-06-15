package com.company.michael.smarteverything.hue.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class AuthenticationRequest {
    @SerializedName("devicetype")
    private String deviceType;
}
