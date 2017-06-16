package com.company.michael.smarteverything;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.company.michael.smarteverything.hue.endpoints.GroupEndpoint;
import com.company.michael.smarteverything.hue.models.SuccessResult;
import com.company.michael.smarteverything.network.TextViewResponseListener;
import com.google.gson.JsonObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID = "Z07jhvR8rylPQGsZklnmMntbMqO2DOTvbF8xUyXU";
    private static final String GROUP_ID = "2";

    private TextView displayView;
    private TextView userIdView;
    private TextView groupIdView;
    private GroupEndpoint groupEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupEndpoint = new GroupEndpoint(this);
        isChecked();
        displayView = (TextView) findViewById(R.id.displayView);
        userIdView = (TextView) findViewById(R.id.user_id);
        groupIdView = (TextView) findViewById(R.id.group_id);


    }

    private void isChecked() {
        final Response.Listener<JsonObject> listener = new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                Switch lightSwitch = (Switch) findViewById(R.id.light_switch);
                lightSwitch.setChecked(response.getAsJsonObject("state").get("any_on").getAsBoolean());
                lightSwitch.setOnCheckedChangeListener(new LightOnCheckedChangedListener());

            }
        };

        groupEndpoint.getGroup(USER_ID, GROUP_ID, listener);
    }

    private class LightOnCheckedChangedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            TextViewResponseListener<List<SuccessResult<JsonObject>>> textViewResponseListener = new TextViewResponseListener<>(displayView);
            groupEndpoint.setGroupsLight(userIdView.getText().toString(), groupIdView.getText().toString(), isChecked, textViewResponseListener);
        }
    }
}
