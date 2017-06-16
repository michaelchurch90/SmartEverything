package com.company.michael.smarteverything.hue.endpoints;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.company.michael.smarteverything.hue.models.OnOffRequest;
import com.company.michael.smarteverything.hue.models.SuccessResult;
import com.company.michael.smarteverything.network.GsonRequest;
import com.company.michael.smarteverything.network.RequestQueueFactory;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GroupEndpoint {
    private static final String SCHEME = "http";
    private static final String IP = "192.168.1.188";
    private static final String API_PATH = "api";
    private static final String GROUP_PATH = "groups";
    private static final String ACTION_PATH = "action";

    private final Context context;
    public GroupEndpoint(Context context) {
        this.context = context;
    }

    public void getGroup(String userId, String groupId, Response.Listener<JsonObject> responseHandler) {
        final RequestQueue requestQueue = RequestQueueFactory.getInstance(context);

        final Uri uri =  new Uri.Builder()
                .scheme(SCHEME)
                .path(IP)
                .appendPath(API_PATH)
                .appendPath(userId)
                .appendPath(GROUP_PATH)
                .appendPath(groupId)
                .build();

        final GsonRequest<OnOffRequest, JsonObject> gsonRequest = new GsonRequest<>(Request.Method.GET, uri.toString(), null, responseHandler, new TypeToken<JsonObject>(){}, null);
        requestQueue.add(gsonRequest);
    }

    public void setGroupsLight(String userId, String groupId, boolean setOn, Response.Listener<List<SuccessResult<JsonObject>>> responseHandler) {
        final RequestQueue requestQueue = RequestQueueFactory.getInstance(context);
        final OnOffRequest onOffRequest = new OnOffRequest(setOn);

        final Uri uri =  new Uri.Builder()
                .scheme(SCHEME)
                .path(IP)
                .appendPath(API_PATH)
                .appendPath(userId)
                .appendPath(GROUP_PATH)
                .appendPath(groupId)
                .appendPath(ACTION_PATH)
                .build();

        final GsonRequest<OnOffRequest, List<SuccessResult<JsonObject>>> gsonRequest = new GsonRequest<>(Request.Method.PUT, uri.toString(),
                null, responseHandler, new TypeToken<List<SuccessResult<JsonObject>>>() {
        }, onOffRequest);

        requestQueue.add(gsonRequest);
    }


}
