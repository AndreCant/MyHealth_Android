package it.mwt.myhealth.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.R;
import it.mwt.myhealth.adapter.DoctorsRecyclerViewAdapter;
import it.mwt.myhealth.model.Doctor;

public class DoctorsFragment extends Fragment {

    private DoctorViewModel viewModel;
    private List<Doctor> data = new ArrayList<>();
    private DoctorsRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        this.setupRecyclerView(view);
    }

    public void setupRecyclerView (View view){
        adapter = new DoctorsRecyclerViewAdapter(this.data);

        adapter.setOnDoctorSelected(new DoctorsRecyclerViewAdapter.OnDoctorSelected() {
            @Override
            public void onSelected(Doctor doctor) {
                viewModel.setSelectedDoctor(doctor);

                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_doctors_to_doctor);
            }
        });

        RecyclerView recyclerView = getActivity().findViewById(R.id.full_doctors);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getDoctorList().observe(getViewLifecycleOwner(), new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> doctors) {
                data.clear();
                data.addAll(doctors);
                adapter.notifyDataSetChanged();
            }
        });
    }
}