package it.mwt.myhealth.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class ExamRequest {

    private static final String BASE_URL="http://10.0.2.2:8080/myhealth/rest";
    private static final String EXAMS=BASE_URL+"/public/exams";
    private static final String EXAMS_TYPE_REHABILITATION=BASE_URL+"/public/exams?type=rehabilitation ";
    private static final String EXAMS_TYPE_EXAM=BASE_URL+"/public/exams?type=";


    private static ExamRequest instance = null;

    public synchronized static ExamRequest getInstance() {
        return instance == null ? instance = new ExamRequest() : instance;
    }


    public void getExams(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                EXAMS,
                jsonRequest,
                responseListener,
                errorListener
        );

        VolleyRequest.getInstance(context).getQueue().add(request);
    }

    public void getExamsType(Context context, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, String type){

        JsonObjectRequest request = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                EXAMS_TYPE_EXAM+type,
                jsonRequest,
                responseListener,
                errorListener
        );

        VolleyRequest.getInstance(context).getQueue().add(request);
    }

}
