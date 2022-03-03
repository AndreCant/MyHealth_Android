package it.mwt.myhealth.ui.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.doctor.DoctorActivity;
import it.mwt.myhealth.ui.exam.ExamsActivity;

public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private LinearLayout linearLayout;
    private LinearLayout linearLayoutRehabilitations;
    private LinearLayout linearLayoutExam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) getView().findViewById(R.id.all_exams);
        linearLayoutRehabilitations = (LinearLayout)  getView().findViewById(R.id.rehabilitations_path);
        linearLayoutExam = (LinearLayout)  getView().findViewById(R.id.exams);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutRehabilitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamsActivity.class);
                intent.putExtra("type", "path");
                startActivity(intent);
            }
        });

        linearLayoutExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamsActivity.class);
                intent.putExtra("type", "exam");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {}
}