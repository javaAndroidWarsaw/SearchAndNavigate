package com.softwareacademy.searchandnavigate.login.mvp;

import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;
import com.softwareacademy.searchandnavigate.utils.shared_preferences.SharedPreferencesFacade;

/**
 *
 */

public class LoginModel implements LoginMVP.Model {


    private SharedPreferencesFacade sharedPreferencesFacade;

    public LoginModel(SharedPreferencesFacade sharedPreferencesFacade) {
        this.sharedPreferencesFacade = sharedPreferencesFacade;
    }

    @Override
    public boolean saveUserData(UserProfileDto userProfileDto) {
        return false;
    }
}
