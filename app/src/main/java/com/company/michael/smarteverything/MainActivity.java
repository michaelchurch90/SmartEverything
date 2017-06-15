package com.company.michael.smarteverything;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.company.michael.smarteverything.hue.models.OnOffRequest;
import com.company.michael.smarteverything.hue.models.SuccessResult;
import com.company.michael.smarteverything.network.GsonRequest;
import com.company.michael.smarteverything.network.RequestQueueFactory;
import com.company.michael.smarteverything.network.TextViewResponseListener;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String IP = "192.168.1.188";
    private static final String USER_ID = "Z07jhvR8rylPQGsZklnmMntbMqO2DOTvbF8xUyXU";
    private static final String GROUP_INFO_URL = "http://192.168.1.188/api/Z07jhvR8rylPQGsZklnmMntbMqO2DOTvbF8xUyXU/groups/2";
    private static final String GROUP_ACTION_URL = "http://192.168.1.188/api/Z07jhvR8rylPQGsZklnmMntbMqO2DOTvbF8xUyXU/groups/2/action";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isChecked(this);


    }

    private void isChecked(final Context context) {


        final RequestQueue requestQueue = RequestQueueFactory.getInstance(context);



        final GsonRequest<OnOffRequest, JsonObject> gsonRequest = new GsonRequest<>(Request.Method.GET, GROUP_INFO_URL, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {

                Switch lightSwitch = (Switch) findViewById(R.id.light_switch);
                lightSwitch.setChecked(response.getAsJsonObject("state").get("any_on").getAsBoolean());
                lightSwitch.setOnCheckedChangeListener(new LightOnCheckedChangedListener(context));

            }
        }, new TypeToken<JsonObject>(){}, null);

        requestQueue.add(gsonRequest);

    }

    private class LightOnCheckedChangedListener implements CompoundButton.OnCheckedChangeListener {

        private final Context context;

        private LightOnCheckedChangedListener(Context context) {
            this.context = context;
        }


        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            final TextView textView = (TextView) findViewById(R.id.displayView);

            final RequestQueue requestQueue = RequestQueueFactory.getInstance(context);


            //IP and user id that was setup on my network when I did hue setup


            TextViewResponseListener<List<SuccessResult<JsonObject>>> textViewResponseListener = new TextViewResponseListener<>(textView);
            OnOffRequest onOffRequest = new OnOffRequest(isChecked);

            final GsonRequest<OnOffRequest, List<SuccessResult<JsonObject>>> gsonRequest = new GsonRequest<>(Request.Method.PUT, GROUP_ACTION_URL, textViewResponseListener, textViewResponseListener, new TypeToken<List<SuccessResult<JsonObject>>>() {
            }, onOffRequest);

            requestQueue.add(gsonRequest);


        }
    }
}
