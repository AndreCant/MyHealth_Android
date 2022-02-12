package it.mwt.myhealth.ui.exams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.volley.ExamRequest;

public class ExamsActivity extends AppCompatActivity {

    private ExamViewModel viewModel;
    private List<Exam> data = new ArrayList<>();
    private ExamsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        Intent intent = getIntent();
        intent.getSerializableExtra("type");
        viewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        viewModel.setType(intent.getSerializableExtra("type").toString());
        viewModel.retrieveData(getApplicationContext());


        //String[] exams = {"torino", "roma", "torino", "roma","torino", "roma" ,"torino", "roma"};
        this.setupRecyclerView();
    }

    public void setupRecyclerView (){
        adapter = new ExamsRecyclerViewAdapter(this.data);

        adapter.setOnExamSelected(new ExamsRecyclerViewAdapter.OnExamSelected() {
            @Override
            public void onSelected(Exam exam) {
                viewModel.setSelectedExam(exam);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.full_exams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getExamList().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                data.clear();
                data.addAll(exams);
                adapter.notifyDataSetChanged();
            }
        });
    }
}