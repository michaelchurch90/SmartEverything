package com.company.michael.smarteverything.hue.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class HueUser {
    private String userName;
}
