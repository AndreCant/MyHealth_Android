package it.mwt.myhealth.volley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class DoctorRequest {

    private static final String BASE_URL="http://10.0.2.2:8080/myhealth/rest";
    private static final String DOCTORS=BASE_URL+"/public/doctors";

    private static DoctorRequest instance = null;

    public synchronized static DoctorRequest getInstance() {
        return instance == null ? instance = new DoctorRequest() : instance;
    }

    public void getDoctors(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                DOCTORS,
                null,
                responseListener,
                errorListener
        );

        VolleyRequest.getInstance(context).getQueue().add(request);
    }
}
