package it.mwt.myhealth.ui.myExam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.adapter.MyExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Reservation;

import it.mwt.myhealth.R;

public class MyExamsFragment extends Fragment {

    private MyExamsViewModel viewModel;
    private List<Reservation> data = new ArrayList<>();
    private MyExamsRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_exams, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MyExamsViewModel.class);
        this.setupRecyclerView(view);
    }

    public void setupRecyclerView (View view){
        adapter = new MyExamsRecyclerViewAdapter(this.data);

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
