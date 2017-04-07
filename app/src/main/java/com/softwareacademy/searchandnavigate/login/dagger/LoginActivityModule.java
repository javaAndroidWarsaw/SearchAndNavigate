package com.softwareacademy.searchandnavigate.login.dagger;

import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.login.mvp.LoginMVP;
import com.softwareacademy.searchandnavigate.login.mvp.LoginModel;
import com.softwareacademy.searchandnavigate.login.mvp.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 *
 */

@Module
public class LoginActivityModule {
    LoginMVP.View view;

    public LoginActivityModule(LoginMVP.View view) {
        this.view = view;
    }


    @Provides
    @ActivityScope
    LoginMVP.Presenter providePresenter(LoginModel loginModel) {
        return new LoginPresenter(view, loginModel);
    }
}
