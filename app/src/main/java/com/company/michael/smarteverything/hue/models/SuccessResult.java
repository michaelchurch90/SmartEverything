package com.company.michael.smarteverything.hue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessResult <T> {

    @JsonProperty
    private T success;

    public T getSuccess() {
        return success;
    }
}
