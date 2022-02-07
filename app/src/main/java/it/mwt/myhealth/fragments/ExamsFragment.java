package it.mwt.myhealth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;

public class ExamsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_exams, container, false);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] exams ={"Cuore", "spalla", "braccio", "Cuore", "spalla", "braccio","Cuore", "spalla", "braccio","Cuore", "spalla", "braccio"};


        RecyclerView recyclerView = getView().findViewById(R.id.exams_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new ExamsRecyclerViewAdapter(exams));
    }

}
