package com.company.michael.smarteverything.hue;

import android.content.Context;
import android.widget.TextView;

import com.company.michael.smarteverything.hue.models.HueUser;
import com.company.michael.smarteverything.hue.models.SuccessResult;

public interface Authenticator {
    void authenticate(Context context, TextView outputView);
}
