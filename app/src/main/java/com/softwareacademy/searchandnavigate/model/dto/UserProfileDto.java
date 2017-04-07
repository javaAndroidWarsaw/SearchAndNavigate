package com.softwareacademy.searchandnavigate.model.dto;

import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.gson.annotations.Expose;

/**
 *
 */

public class UserProfileDto {
    @Expose
    private String userName;
    @Expose
    private String userPhotoUrl;

    public UserProfileDto(GoogleSignInResult googleSignInResult){
        userName  = googleSignInResult.getSignInAccount().getDisplayName();
        Uri photoUrl = googleSignInResult.getSignInAccount().getPhotoUrl();
        if(photoUrl !=null)
        userPhotoUrl = photoUrl.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfileDto)) return false;

        UserProfileDto that = (UserProfileDto) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null)
            return false;
        return userPhotoUrl != null ? userPhotoUrl.equals(that.userPhotoUrl) : that.userPhotoUrl == null;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userPhotoUrl != null ? userPhotoUrl.hashCode() : 0);
        return result;
    }
}
