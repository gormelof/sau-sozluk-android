package gormelof.net.sausozluk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import gormelof.net.sausozluk.views.ui.entrypoint.EntryPointActivity;

public class UserSession {

    SharedPreferences mSharedPreferences;
    Editor mEditor;
    Context mContext;

    int PRIVATE_MODE = 0;

    public static final String PREFERENCES_NAME = "SauSozlukUserSession";

    public static final String IS_USER_LOGIN = "isUserLoggedIn";

    public static final String KEY_USER_ID = "userId";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_USERNAME = "username";

    public static final String KEY_USERNAME_SLUG = "usernameSlug";

    public static final String KEY_TOKEN = "token";

    public UserSession(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREFERENCES_NAME, PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();
    }

    public void createUserSession(String userId, String email, String username, String usernameSlug, String token) {
        mEditor.putBoolean(IS_USER_LOGIN, true);
        mEditor.putString(KEY_USER_ID, userId);
        mEditor.putString(KEY_EMAIL, email);
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_USERNAME_SLUG, usernameSlug);
        mEditor.putString(KEY_TOKEN, token);
        mEditor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USER_ID, mSharedPreferences.getString(KEY_USER_ID, null));
        user.put(KEY_EMAIL, mSharedPreferences.getString(KEY_EMAIL, null));
        user.put(KEY_USERNAME, mSharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_USERNAME_SLUG, mSharedPreferences.getString(KEY_USERNAME_SLUG, null));
        user.put(KEY_TOKEN, mSharedPreferences.getString(KEY_TOKEN, null));

        return user;
    }

    public String getToken() {
        return mSharedPreferences.getString(KEY_TOKEN, null);
    }

    public void clearUserData() {
        mEditor.clear();
        mEditor.commit();
    }

    public boolean isUserLoggedIn() {
        return mSharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

}
