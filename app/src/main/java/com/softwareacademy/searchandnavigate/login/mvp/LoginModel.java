package com.softwareacademy.searchandnavigate.login.mvp;

import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;
import com.softwareacademy.searchandnavigate.utils.shared_preferences.SharedPreferencesFacade;

import javax.inject.Inject;

/**
 *
 */

@ActivityScope
public class LoginModel implements LoginMVP.Model {


    public static final String USER_KEY = "userKey";
    private SharedPreferencesFacade sharedPreferencesFacade;

    /**
     * W ten sposób nie trzeba deklarować w żadnym module , tylko przez adnotacje konstruktora można używać
     *
     */

    @Inject
    public LoginModel(SharedPreferencesFacade sharedPreferencesFacade) {
        this.sharedPreferencesFacade = sharedPreferencesFacade;
    }

    @Override
    public boolean saveUserData(UserProfileDto userProfileDto) {
        sharedPreferencesFacade.putObject(USER_KEY, userProfileDto);
        return true;
    }

    @Override
    public UserProfileDto retrieveUserData() {
        return sharedPreferencesFacade.getObject(USER_KEY,UserProfileDto.class);
    }

    @Override
    public boolean isLogged() {
        return sharedPreferencesFacade.getObject(USER_KEY,UserProfileDto.class) != null;
    }
}
