package com.softwareacademy.searchandnavigate.login.mvp;

import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;

/**
 *
 */

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private LoginMVP.Model model;


    /**
     * Login Presenter, odpowiedzialny za komunikacje pomiędzy modelem a widokiem
     *
     */
    public LoginPresenter(LoginMVP.View view, LoginMVP.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void checkIsUserLogged() {
        if (model.isLogged()) view.userLogged();
    }

    @Override
    public void saveUserData(UserProfileDto userProfileDto) {
        if (model.saveUserData(userProfileDto)) {
            view.userLogged();
        }
    }
}
