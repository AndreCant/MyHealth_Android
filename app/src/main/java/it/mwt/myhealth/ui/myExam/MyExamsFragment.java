package it.mwt.myhealth.ui.myExam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.ExamRequest;

import it.mwt.myhealth.R;

public class MyExamsFragment extends Fragment {

    private HashMap exams = new HashMap();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_exams, container, false);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] exams ={"Cuore", "spalla", "braccio", "Cuore", "spalla", "braccio","Cuore", "spalla", "braccio","Cuore", "spalla", "braccio"};

//    ExamRequest.getInstance().getExams(getContext(),
//            null,
//            response -> {
//                    System.out.println("success");
//            },
//            error->{
//
//                System.out.println("error");
//
//                if(error.networkResponse.statusCode == 401){
//                    Preferences.setUser(getContext(), null);
//                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//            );
//
//        RecyclerView recyclerView = getView().findViewById(R.id.exams_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //recyclerView.setAdapter(new ExamsRecyclerViewAdapter(exams));
    }

}
