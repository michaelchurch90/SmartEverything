package com.company.michael.smarteverything;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.company.michael.smarteverything.hue.Authenticator;
import com.company.michael.smarteverything.hue.HueAuthenticator;
import com.company.michael.smarteverything.hue.models.OnOffRequest;
import com.company.michael.smarteverything.network.GsonRequest;
import com.company.michael.smarteverything.network.RequestQueueFactory;
import com.company.michael.smarteverything.network.SuccessResponse;
import com.company.michael.smarteverything.network.TextViewResponseListener;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendRequest(View view) {
        final TextView textView = (TextView) findViewById(R.id.displayView);
        final TextView authView = (TextView) findViewById(R.id.user_id);

        final RequestQueue requestQueue = RequestQueueFactory.getInstance(this);




        //IP and user id that was setup on my network when I did hue setup
        final String url = "http://192.168.1.188/api/Z07jhvR8rylPQGsZklnmMntbMqO2DOTvbF8xUyXU/groups/2/action";


        TextViewResponseListener<List<SuccessResponse>> textViewResponseListener = new TextViewResponseListener<>(textView);
        OnOffRequest onOffRequest = new OnOffRequest(false);

        final GsonRequest<OnOffRequest, List<SuccessResponse>> gsonRequest = new GsonRequest<>(Request.Method.PUT, url, textViewResponseListener, textViewResponseListener, new TypeToken<List<SuccessResponse>>(){},  onOffRequest);

        requestQueue.add(gsonRequest);
//        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                textView.setText("Response is: " + response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!" + error);
//            }
//        });
//
//
//        requestQueue.add(stringRequest);
    }

    public void authenticate(View view) {
        final TextView authView = (TextView) findViewById(R.id.user_id);
        Authenticator authenticator = new HueAuthenticator();
        authenticator.authenticate(this, authView);

    }
}
