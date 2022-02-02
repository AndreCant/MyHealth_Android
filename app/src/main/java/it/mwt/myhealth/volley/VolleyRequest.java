package it.mwt.myhealth.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRequest {

    private static VolleyRequest instance = null;

    public synchronized static VolleyRequest getInstance(Context context) {
        return instance == null ? instance = new VolleyRequest(context) : instance;
    }

    private RequestQueue queue;

    private VolleyRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    protected RequestQueue getQueue() {
        return this.queue;
    }
}
