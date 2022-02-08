package it.mwt.myhealth.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import it.mwt.myhealth.model.User;

public class Preferences {

    private static final String LOGGED = "LOGGED";
    private static final String TOKEN = "TOKEN";
    private static final String USERNAME = "USERNAME";
    private static final String ID = "ID";
    private static final String EMAIL = "EMAIL";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String FISCAL_CODE = "FISCAL_CODE";
    private static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    private static final String GENDER = "GENDER";
    private static final String FIRST_START_LOCATION = "FIRST_START_LOCATION";


    private static SharedPreferences getPref(Context context){
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultSharedPreferences;
    }

    public static void setUser(Context context, User user){
        SharedPreferences.Editor editor = getPref(context).edit();

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

    public static void setUserInfo(Context context, User user){
        SharedPreferences.Editor editor = getPref(context).edit();

        if(user != null){
            editor.putLong(ID, user.getId());
            editor.putString(EMAIL, user.getEmail());
            editor.putString(NAME, user.getName());
            editor.putString(SURNAME, user.getSurname());
            editor.putString(FISCAL_CODE, user.getFiscalCode());
            editor.putString(DATE_OF_BIRTH, user.getDateOfBirth());
            editor.putString(GENDER, user.getGender());
        }else {
            editor.putLong(ID, 0);
            editor.putString(EMAIL, "");
            editor.putString(NAME, "");
            editor.putString(SURNAME, "");
            editor.putString(FISCAL_CODE, "");
            editor.putString(DATE_OF_BIRTH, "");
            editor.putString(GENDER, "");
        }
        editor.apply();
    }

    public static boolean isLogged(Context context){
        return getPref(context).getBoolean(LOGGED, false);
    }

    public static String getToken(Context context){
        return getPref(context).getString(TOKEN,"");
    }

    public static long getId(Context context){
        return getPref(context).getLong(ID,0);
    }

    public static String getUsername(Context context){
        return getPref(context).getString(USERNAME,"");
    }

    public static String getEmail(Context context){
        return getPref(context).getString(EMAIL,"");
    }

    public static String getName(Context context){
        return getPref(context).getString(NAME,"");
    }

    public static String getSurname(Context context){
        return getPref(context).getString(SURNAME,"");
    }

    public static String getFiscalCode(Context context){
        return getPref(context).getString(FISCAL_CODE,"");
    }

    public static String getDateOfBirth(Context context){
        return getPref(context).getString(DATE_OF_BIRTH,"");
    }

    public static String getGender(Context context){
        return getPref(context).getString(GENDER,"");
    }

    public static boolean getFirstStartLocation(Context context){
        return getPref(context).getBoolean(FIRST_START_LOCATION,true);
    }

    public static void setFirstStartLocation(Context context, boolean isFirstStart){
        getPref(context).edit().putBoolean(FIRST_START_LOCATION, isFirstStart).apply();
    }

}
