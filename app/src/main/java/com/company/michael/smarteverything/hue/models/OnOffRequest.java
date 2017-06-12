package com.company.michael.smarteverything.hue.models;

public class OnOffRequest {
    private final boolean on;


    public OnOffRequest(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}
