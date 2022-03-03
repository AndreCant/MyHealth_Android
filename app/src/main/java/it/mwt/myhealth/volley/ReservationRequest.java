package it.mwt.myhealth.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.mwt.myhealth.util.Preferences;

public class ReservationRequest {

    private static final String BASE_URL="http://10.0.2.2:8080/myhealth/rest";
    private static final String RESERVATIONS=BASE_URL+"/private/reservations";
    private static final String EXAM=BASE_URL+"/private/exam";
    private static ReservationRequest instance = null;

    public synchronized static ReservationRequest getInstance() {
        return instance == null ? instance = new ReservationRequest() : instance;
    }

    public void getReservations(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                RESERVATIONS,
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

    public void insert(Context context, String day, String hour, long examId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){
        String requestUri = EXAM + "/" + examId + "/reservation?day=" + day + "&hour=" + hour;

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.POST,
                requestUri,
                null,
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
