package it.mwt.myhealth.ui.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;

public class ExamsActivity extends AppCompatActivity {

    private ExamViewModel viewModel;
    private List<Exam> data = new ArrayList<>();
    private ExamsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        viewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        viewModel.setType("all");
        viewModel.retrieveData(getApplicationContext());

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