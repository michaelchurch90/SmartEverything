package com.company.michael.smarteverything.hue;

import com.company.michael.smarteverything.hue.models.HueUser;
import com.company.michael.smarteverything.hue.models.SuccessResult;

public interface Authenticator {
    SuccessResult<HueUser> authenticate();
}
