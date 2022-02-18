package it.mwt.myhealth.ui.myExam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.category.CategoriesFragment;
import it.mwt.myhealth.ui.exam.ExamViewModel;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.Preferences;

public class MyExamsActivity extends AppCompatActivity {

    private MyExamsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exams);
        getSupportActionBar().hide();

        if (!Preferences.isLogged(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            viewModel = new ViewModelProvider(this).get(MyExamsViewModel.class);
            viewModel.retrieveData(getApplicationContext());
        }
    }
}
