package it.mwt.myhealth.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.mwt.myhealth.util.Preferences;

public class UserRequest {

    private static final String BASE_URL="http://10.0.2.2:8080/myhealth/rest";
    private static final String LOGIN=BASE_URL+"/public/login";
    private static final String REGISTRATION=BASE_URL+"/public/registration";
    private static final String PROFILE=BASE_URL+"/private/profile";

    private static UserRequest instance = null;

    public synchronized static UserRequest getInstance() {
        return instance == null ? instance = new UserRequest() : instance;
    }

    /**
     * Login user
     * */
    public void login(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.POST,
                LOGIN,
                jsonRequest,
                responseListener,
                errorListener
        );

        VolleyRequest.getInstance(context).getQueue().add(request);
    }

    public void registration(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.POST,
                REGISTRATION,
                jsonRequest,
                responseListener,
                errorListener
        );

        VolleyRequest.getInstance(context).getQueue().add(request);
    }

    public void profile(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                PROFILE,
                jsonRequest,
                responseListener,
                errorListener
        ){
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + Preferences.getToken(context));
                return headers;
            }
        };;

        VolleyRequest.getInstance(context).getQueue().add(request);
    }
}
