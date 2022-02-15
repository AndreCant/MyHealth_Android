package it.mwt.myhealth.ui.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;

public class ExamsActivity extends AppCompatActivity {

    private ExamViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        getSupportActionBar().hide();

        viewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        viewModel.setType(getIntent().getSerializableExtra("type").toString());
        viewModel.retrieveData(getApplicationContext());
    }
}