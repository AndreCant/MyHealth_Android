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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import it.mwt.myhealth.R;
import it.mwt.myhealth.databinding.FragmentExamBinding;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ImageLoadTask;

public class ExamFragment extends Fragment {

    private ExamViewModel viewModel;
    private Exam exam;

    private ImageView image;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView typeTextView;
    private TextView descriptionTextView;
    private TextView dateInput;
    private TextView timeInput;
    private Button bookBtn;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
        dateInput = view.findViewById(R.id.exam_date);
        timeInput = view.findViewById(R.id.exam_time);
        bookBtn = view.findViewById(R.id.exam_btn);

        viewModel.getSelectedExam().observe(getViewLifecycleOwner(), new Observer<Exam>() {
            @Override
            public void onChanged(Exam exam) {
                if(exam != null) {
                    if (exam.getImageUrl() != null) new ImageLoadTask(exam.getImageUrl(), image).execute();
                    descriptionTextView.setText(exam.getDescription());
                    nameTextView.setText(exam.getName());
                    priceTextView.setText("€" + exam.getPrice());
                    typeTextView.setText(exam.getSpecialization() + ", " + exam.getSubSpecialization());
                }
            }
        });

        dateInput.setText("Select Date...");
        timeInput.setText("Select Hour...");

        dateInput.setOnClickListener(view1 -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        timeInput.setOnClickListener(view1 -> {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(
                    getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            timeInput.setText(hourOfDay + ":" + minute);
                        }
            }, hour, minute, false);
            dialog.show();
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "/" + month + "/" + year;
                dateInput.setText(date);
            }
        };
    }
}