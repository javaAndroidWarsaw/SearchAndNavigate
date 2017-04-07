package com.softwareacademy.searchandnavigate.login.mvp;

import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;

/**
 *
 */

public interface LoginMVP {
    interface Model {
        boolean saveUserData(UserProfileDto userProfileDto);

        UserProfileDto retrieveUserData();

        boolean isLogged();
    }

    interface Presenter {
        void checkIsUserLogged();

        void saveUserData(UserProfileDto userProfileDto);
    }

    interface View {
        void userLogged();
    }
}
