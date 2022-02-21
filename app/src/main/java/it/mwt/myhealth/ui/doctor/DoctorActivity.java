package it.mwt.myhealth.ui.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import it.mwt.myhealth.R;

public class DoctorActivity extends AppCompatActivity {

    private DoctorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();

        viewModel = new ViewModelProvider(this).get(DoctorViewModel.class);
        viewModel.retrieveData(getApplicationContext());
    }
}