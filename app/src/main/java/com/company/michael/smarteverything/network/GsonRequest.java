package com.company.michael.smarteverything.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonRequest<T, U> extends Request<U> {
    private final Gson gson = new Gson();
    private final Class<U> responseClass;
    private final Response.Listener<U> listener;
    private final T body;
    private final TypeToken<U> type;


    public GsonRequest(int method, String url,
                       Response.ErrorListener errorListener,
                       Response.Listener<U> responseListener,
                       Class<U> responseClass,
                       T body) {
        super(method, url, errorListener);
        this.responseClass = responseClass;
        this.listener = responseListener;
        this.body = body;
        this.type = null;
    }

    public GsonRequest(int method, String url,
                       Response.ErrorListener errorListener,
                       Response.Listener<U> responseListener,
                       TypeToken<U> responseClass,
                       T body) {
        super(method, url, errorListener);
        this.responseClass = null;
        this.listener = responseListener;
        this.body = body;
        type = responseClass;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return gson.toJson(body).getBytes();
    }

    @Override
    protected Response<U> parseNetworkResponse(NetworkResponse response) {
        final String json = new String(response.data);

        U deserialized;

        if(responseClass != null) {
            deserialized = gson.fromJson(json, responseClass);
        } else if(type != null) {
            deserialized = (U) gson.fromJson(json, type.getClass());
        } else {
            throw new IllegalStateException("Both response class and type are null");
        }

        return Response.success(
                deserialized,
                HttpHeaderParser.parseCacheHeaders(response)
        );
    }

    @Override
    protected void deliverResponse(U response) {
        listener.onResponse(response);
    }
}
