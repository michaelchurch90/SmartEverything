package com.company.michael.smarteverything.network;

import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

public class TextViewResponseListener<T> implements Response.Listener<T>, Response.ErrorListener {
    private final Gson gson = new Gson();

    private final TextView outputView;

    public TextViewResponseListener(TextView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void onResponse(T response) {
        outputView.setText(gson.toJson(response));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        outputView.setText(error.getMessage());

    }
}
