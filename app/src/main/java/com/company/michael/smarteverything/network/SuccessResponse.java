package com.company.michael.smarteverything.network;

import java.util.Map;

public class SuccessResponse {

    private Map<String, Object> success;

    public SuccessResponse() {
    }

    public SuccessResponse(Map<String, Object> success) {
        this.success = success;
    }

    public Map<String, Object> getSuccess() {
        return success;
    }
}
