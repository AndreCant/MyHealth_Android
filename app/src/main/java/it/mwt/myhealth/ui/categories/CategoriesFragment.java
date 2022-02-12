package it.mwt.myhealth.ui.categories;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.ui.exams.ExamsActivity;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.ExamRequest;

public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private LinearLayout linearLayout;
    private LinearLayout linearLayoutCategories;
    private LinearLayout linearLayoutExam;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) getView().findViewById(R.id.all_exams);
        linearLayoutCategories = (LinearLayout)  getView().findViewById(R.id.rehabilitations_path);
        linearLayoutExam = (LinearLayout)  getView().findViewById(R.id.rehabilitations_path);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamsActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamsActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamsActivity.class);
                startActivity(intent);
            }
        });




    }


    @Override
    public void onClick(View view) {

    }
}