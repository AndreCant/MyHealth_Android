package it.mwt.myhealth.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import it.mwt.myhealth.model.User;

public class ParseJSON {

    private static final String TAG = ParseJSON.class.getSimpleName();

    public static JSONObject login2JSON(String username, String password) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username",username);
            jsonRequest.put("password",password);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }

        return jsonRequest;
    }

    public static User json2user(JSONObject response) throws JSONException {

        String token = response.getString("token");
        String username = response.getString("username");

        User user = new User();
        user.setUsername(username);
        user.setToken(token);

        return user;
    }

    public static JSONObject register2JSON(String username, String email, String password, String fiscalCode) {

        JSONObject jsonUser = new JSONObject();
        try {
            if(username != null) jsonUser.put("username", username);
            if(email != null) jsonUser.put("email", email);
            if(password != null) jsonUser.put("password", password);
            if(fiscalCode != null) jsonUser.put("fiscalCode", fiscalCode);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }

        return jsonUser;
    }
}
