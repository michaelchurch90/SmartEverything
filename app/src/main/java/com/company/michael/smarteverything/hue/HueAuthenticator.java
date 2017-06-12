package com.company.michael.smarteverything.hue;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.michael.smarteverything.hue.models.AuthenticationRequest;
import com.company.michael.smarteverything.network.RequestQueueFactory;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class HueAuthenticator implements Authenticator {

    private final int HTTP_METHOD = Request.Method.POST;

    //This ip will need to be retrieved
    private static final String AUTH_URL = "http://192.168.1.188/api";
    private static final String APP_NAME = "testapp#computer mike";


    @Override
    public void authenticate(final Context context, final TextView outputView) {


        final AuthenticationRequest requestBody = new AuthenticationRequest(APP_NAME);

        final RequestQueue requestQueue = RequestQueueFactory.getInstance(context);


        Gson gson = new Gson();

        JSONObject json;
        try {
            json = new JSONObject(gson.toJson(requestBody));
        } catch (JSONException e) {
            throw new IllegalStateException("Unable to convert object to json");
        }
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HTTP_METHOD, AUTH_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                outputView.setText("Response is: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                outputView.setText("That didn't work!" + error);
            }
        });


        requestQueue.add(jsonObjectRequest);
    }
}
