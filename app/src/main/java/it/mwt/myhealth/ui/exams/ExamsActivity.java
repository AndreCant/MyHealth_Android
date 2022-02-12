package it.mwt.myhealth.ui.exams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONException;

import java.util.ArrayList;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.volley.ExamRequest;

public class ExamsActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);



        //String[] exams = {"torino", "roma", "torino", "roma","torino", "roma" ,"torino", "roma"};

        ExamRequest.getInstance().getExams(
                getApplicationContext(),
                null,
                response -> new Thread(() -> {
                    ArrayList<Exam> exams = null;
                    try {
                        exams = ParseJSON.json2exam(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayList<Exam> finalExams = exams;
                    // da vedere se è da utilizzare qui il RUN_ON_UI_THREAD;
                    runOnUiThread(()-> this.setupRecyclerView(finalExams));
                }).start(),
                error -> new Thread(() -> {
                }).start()
        );
    }




    public void setupRecyclerView (ArrayList<Exam> exams){
        RecyclerView recyclerView = findViewById(R.id.full_exams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ExamsRecyclerViewAdapter(exams));
    }

}