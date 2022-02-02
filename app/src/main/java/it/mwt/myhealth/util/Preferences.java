package it.mwt.myhealth.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import it.mwt.myhealth.model.User;

public class Preferences {

    private static final String LOGGED = "LOGGED";
    private static final String TOKEN = "TOKEN";
    private static final String USERNAME = "USER_NAME";

    public static void setUser(Context context, User user){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        if(user != null){
            editor.putBoolean(LOGGED, true);
            editor.putString(TOKEN, user.getToken());
            editor.putString(USERNAME, user.getUsername());
        }else {
            editor.putBoolean(LOGGED, false);
            editor.putString(TOKEN, "");
            editor.putString(USERNAME, "");
        }
        editor.apply();
    }

    public static boolean isLogged(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(LOGGED, false);
    }
}
