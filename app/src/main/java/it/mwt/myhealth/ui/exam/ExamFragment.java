package it.mwt.myhealth.ui.exam;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import it.mwt.myhealth.R;
import it.mwt.myhealth.databinding.FragmentExamBinding;
import it.mwt.myhealth.model.Exam;

public class ExamFragment extends Fragment {

   private ExamViewModel viewModel;
   private Exam exam;

   private ImageView image;
   private TextView nameTextView;
   private TextView priceTextView;
   private TextView typeTextView;
   private TextView descriptionTextView;
   private EditText datetimeInput;
   private Button bookBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exam, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ExamViewModel.class);

        image = view.findViewById(R.id.exam_image);
        nameTextView = view.findViewById(R.id.exam_name);
        priceTextView = view.findViewById(R.id.exam_price);
        typeTextView = view.findViewById(R.id.exam_type);
        descriptionTextView = view.findViewById(R.id.exam_description);
        datetimeInput = view.findViewById(R.id.exam_date);
        bookBtn = view.findViewById(R.id.exam_btn);

        viewModel.getSelectedExam().observe(getViewLifecycleOwner(), new Observer<Exam>() {
            @Override
            public void onChanged(Exam exam) {
                if(exam != null) {
//                    image.setText(city.getName());
//                    descriptionTextView.setText();
                    nameTextView.setText(exam.getName());
                    priceTextView.setText("€" + exam.getPrice());
                    typeTextView.setText(exam.getSpecialization() + ", " + exam.getSubSpecialization());
                }
            }
        });
    }
}