package com.nyxbox.core.auth;

import android.content.Context;
import android.content.SharedPreferences;

/** Android-backed store using SharedPreferences. */
public class PrefsDefaultStore implements DefaultStore {
    private static final String PREF_NAME = "nyxbox_auth";
    private static final String KEY_DEFAULT = "default_login_method";
    private final SharedPreferences sp;

    public PrefsDefaultStore(Context context) {
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getDefault() {
        return sp.getString(KEY_DEFAULT, null);
    }

    @Override
    public void setDefault(String id) {
        sp.edit().putString(KEY_DEFAULT, id).apply();
    }

    @Override
    public void clear() {
        sp.edit().remove(KEY_DEFAULT).apply();
    }
}
