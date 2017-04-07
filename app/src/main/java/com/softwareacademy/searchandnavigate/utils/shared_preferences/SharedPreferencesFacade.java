package com.softwareacademy.searchandnavigate.utils.shared_preferences;

/**
 *
 */

public interface SharedPreferencesFacade {

    void writeString(String value, String key);
    String getString(String key);
}
