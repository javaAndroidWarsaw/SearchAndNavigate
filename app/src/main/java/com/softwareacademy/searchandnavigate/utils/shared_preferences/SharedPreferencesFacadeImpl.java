package com.softwareacademy.searchandnavigate.utils.shared_preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 *
 */

public class SharedPreferencesFacadeImpl implements SharedPreferencesFacade {

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesFacadeImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void writeString(String value, String key) {

    }

    @Override
    public String getString(String key) {
        return null;
    }
}
