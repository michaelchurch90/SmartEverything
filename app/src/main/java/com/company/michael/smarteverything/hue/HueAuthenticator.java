package com.company.michael.smarteverything.hue;

import com.company.michael.smarteverything.hue.models.AuthenticationRequest;
import com.company.michael.smarteverything.hue.models.HueUser;
import com.company.michael.smarteverything.hue.models.SuccessResult;

public class HueAuthenticator implements Authenticator {

    private static final String AUTH_URL = "192.168.1.188/api";
    private static final String APP_NAME = "this_app_name#personName";
    @Override
    public SuccessResult<HueUser> authenticate() {
        final AuthenticationRequest requestBody = new AuthenticationRequest(APP_NAME);
        //Looking into a good http/rest client to use on android. I have used Spring RestTemplate before in java
        //but I think it might be heavy for android. I'm checking out this http://loopj.com/android-async-http/
        return null;
    }
}
