package it.mwt.myhealth.ui.doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import it.mwt.myhealth.R;
import it.mwt.myhealth.model.Doctor;
import it.mwt.myhealth.util.ImageLoadTask;

public class DoctorFragment extends Fragment {

    private DoctorViewModel viewModel;

    private ImageView image;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView specializationTextView;
    private TextView skillsTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        image = view.findViewById(R.id.doctor_image);
        nameTextView = view.findViewById(R.id.doctor_name);
        ageTextView = view.findViewById(R.id.doctor_age);
        specializationTextView = view.findViewById(R.id.doctor_specialization);
        skillsTextView = view.findViewById(R.id.doctor_skills);

        viewModel.getSelectedDoctor().observe(getViewLifecycleOwner(), new Observer<Doctor>() {
            @Override
            public void onChanged(Doctor doctor) {
                if(doctor != null) {
                    System.out.println("DOCTOR IMAGE URL: " + doctor.getImageUrl());

                    if (doctor.getImageUrl() != null) new ImageLoadTask(doctor.getImageUrl(), image).execute();
                    nameTextView.setText(doctor.getName() + " " + doctor.getSurname());
                    ageTextView.setText(String.valueOf(doctor.getAge()));
                    specializationTextView.setText(doctor.getSpecialization());
                    skillsTextView.setText(doctor.getSkills());
                }
            }
        });
    }
}