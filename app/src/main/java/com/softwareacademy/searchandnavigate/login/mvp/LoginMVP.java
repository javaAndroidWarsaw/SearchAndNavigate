package com.softwareacademy.searchandnavigate.login.mvp;

import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;

/**
 *
 */

public interface LoginMVP {
    interface Model{
        boolean saveUserData(UserProfileDto userProfileDto);
    }

    interface Presenter{
        void saveUserData(UserProfileDto userProfileDto);
    }

    interface View{
        void userDataSaved();
    }
}
