package it.mwt.myhealth.util;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

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

    @SuppressLint("NewApi")
    public static User json2user(JSONObject response) throws JSONException {
        JSONObject jsonUser = response.has("user") ? response.getJSONObject("user") : response;
        User user = new User();

        if (jsonUser.has("id")) user.setId(jsonUser.getLong("id"));
        if (jsonUser.has("token")) user.setToken(jsonUser.getString("token"));
        if (jsonUser.has("username")) user.setUsername(jsonUser.getString("username"));
        if (jsonUser.has("email")) user.setEmail(jsonUser.getString("email"));
        if (jsonUser.has("name")) user.setName(jsonUser.getString("name"));
        if (jsonUser.has("surname")) user.setSurname(jsonUser.getString("surname"));
        if (jsonUser.has("fiscalCode")) user.setFiscalCode(jsonUser.getString("fiscalCode"));
        if (jsonUser.has("dateOfBirth")) user.setDateOfBirth(jsonUser.getString("dateOfBirth"));
        if (jsonUser.has("gender")) user.setGender(jsonUser.getString("gender"));

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
