package it.mwt.myhealth.ui.exam;

import android.content.Intent;
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
import it.mwt.myhealth.adapter.ExamsRecyclerViewAdapter;
import it.mwt.myhealth.model.Exam;

public class ExamsFragment extends Fragment {

    private ExamViewModel viewModel;
    private List<Exam> data = new ArrayList<>();
    private ExamsRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exams, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ExamViewModel.class);

        this.setupRecyclerView(view);
    }

    public void setupRecyclerView (View view){
        adapter = new ExamsRecyclerViewAdapter(this.data);

        adapter.setOnExamSelected(new ExamsRecyclerViewAdapter.OnExamSelected() {
            @Override
            public void onSelected(Exam exam) {
                viewModel.setSelectedExam(exam);

                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_exams_to_exam);
            }
        });

        RecyclerView recyclerView = getActivity().findViewById(R.id.full_exams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getExamList().observe(getViewLifecycleOwner(), new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                System.out.println("5");
                data.clear();
                data.addAll(exams);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
