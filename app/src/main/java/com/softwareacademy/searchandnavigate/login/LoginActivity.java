package com.softwareacademy.searchandnavigate.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.login.dagger.DaggerLoginActivityComponent;
import com.softwareacademy.searchandnavigate.login.dagger.LoginActivityModule;
import com.softwareacademy.searchandnavigate.login.mvp.LoginMVP;
import com.softwareacademy.searchandnavigate.map.MapActivity;
import com.softwareacademy.searchandnavigate.model.dto.UserProfileDto;
import com.softwareacademy.searchandnavigate.utils.AppLog;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View,GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1235;
    private GoogleApiClient mGoogleApiClient;


    @Inject
    LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();


        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(v -> {
            signIn();
        });
        presenter.checkIsUserLogged();
    }

    private void inject() {
        DaggerLoginActivityComponent.builder()
                .searchComponent(((SearchApplication)getApplication()).getSearchComponent())
                .loginActivityModule(new LoginActivityModule(this))
                .build().inject(this);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            presenter.saveUserData( new UserProfileDto(result));
        } else {
             new AlertDialog.Builder(this).setTitle(R.string.fail)
                     .setMessage(R.string.unable_to_log)
                     .setNegativeButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                     .show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void userLogged() {
        AppLog.log("LOGIN","user logged in");
        MapActivity.openActivity(this);
        finish();
    }
}
