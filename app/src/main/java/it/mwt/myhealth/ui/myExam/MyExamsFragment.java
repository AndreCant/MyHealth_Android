package it.mwt.myhealth.ui.myExam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.adapter.MyExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.model.Reservation;
import it.mwt.myhealth.ui.exam.ExamViewModel;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.ExamRequest;

import it.mwt.myhealth.R;

public class MyExamsFragment extends Fragment {

    private MyExamsViewModel viewModel;
    private List<Reservation> data = new ArrayList<>();
    private MyExamsRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_my_exams, container, false);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //String[] exams ={"Cuore", "spalla", "braccio", "Cuore", "spalla", "braccio","Cuore", "spalla", "braccio","Cuore", "spalla", "braccio"};
        this.setupRecyclerView();
    }


    public void setupRecyclerView (){
        adapter = new MyExamsRecyclerViewAdapter(this.data);

        adapter.setOnReservationSelected(new MyExamsRecyclerViewAdapter.OnReservationSelected(){
            @Override
            public void onSelected(Reservation reservation) {
                viewModel.setSelectedReservation(reservation);
            }
        });

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.my_exams_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getReservationsList().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                data.clear();
                data.addAll(reservations);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
