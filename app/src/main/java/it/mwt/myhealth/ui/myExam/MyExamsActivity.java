package it.mwt.myhealth.ui.myExam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.exam.ExamViewModel;

public class MyExamsActivity extends AppCompatActivity {

    private MyExamsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exams);
        getSupportActionBar().hide();

        viewModel = new ViewModelProvider(this).get(MyExamsViewModel.class);
        //viewModel.setType(getIntent().getSerializableExtra("type").toString());
        viewModel.retrieveData(getApplicationContext());
    }
}
