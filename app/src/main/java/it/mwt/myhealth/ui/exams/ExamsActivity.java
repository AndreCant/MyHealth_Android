package it.mwt.myhealth.ui.exams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.ui.registration.RegistrationActivity;

public class ExamsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);



        String[] exams = {"torino", "roma", "torino", "roma","torino", "roma" ,"torino", "roma"};

        RecyclerView recyclerView = findViewById(R.id.full_exams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ExamsRecyclerViewAdapter(exams));


    }




}