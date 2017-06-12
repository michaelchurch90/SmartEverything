package com.company.michael.smarteverything.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueFactory {
    private static RequestQueue requestQueue;

    public static RequestQueue getInstance(Context context) {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }

        return requestQueue;
    }
}
