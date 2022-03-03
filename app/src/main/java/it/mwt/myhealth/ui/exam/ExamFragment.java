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
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import org.json.JSONException;

import java.util.Calendar;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.databinding.FragmentExamBinding;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.ui.registration.RegistrationActivity;
import it.mwt.myhealth.util.ImageLoadTask;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.util.Utility;
import it.mwt.myhealth.volley.ReservationRequest;

public class ExamFragment extends Fragment {

    private ExamViewModel viewModel;
    private String date;
    private String time;
    private long examId;

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
                    examId = exam.getId();
                }
            }
        });

        dateInput.setText(R.string.select_date);
        timeInput.setText(R.string.select_hour);

        dateInput.setOnClickListener(view1 -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    getContext(),
                    R.style.datepicker,
                    mDateSetListener,
                    year,month,day);
            dialog.show();
        });

        timeInput.setOnClickListener(view1 -> {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(
                    getContext(),
                    R.style.timepicker,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            time = hourOfDay + ":" + minute;
                            timeInput.setText(time);
                        }
            }, hour, minute, false);
            dialog.show();
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String dateToShow = day + "/" + month + "/" + year;
                String montString = month > 9 ? String.valueOf(month) : "0" + month;
                String dayString = day > 9 ? String.valueOf(day) : "0" + day;

                date = year + "-" + montString + "-" + dayString;
                dateInput.setText(dateToShow);
            }
        };

        bookBtn.setOnClickListener(view1 -> {
            if (Preferences.isLogged(getContext())){
                if (date == null || time == null || date.isEmpty() || time.isEmpty() || examId == 0) {
                    Utility.showDialog(view1, getString(R.string.oops), getString(R.string.dialogMessage1), getString(R.string.ok));
                }else {
                    ReservationRequest.getInstance().insert(
                            getContext(),
                            date,
                            time,
                            examId,
                            response -> new Thread(() -> {
                                getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Utility.showToast(view, getString(R.string.book_ok), Toast.LENGTH_SHORT);
                                    }
                                });
                                getActivity().finish();
                            }).start(),
                            error -> new Thread(() -> {
                                String errorMessage = "";

                                if(error.networkResponse != null){
                                    switch ( error.networkResponse.statusCode){
                                        case 400:
                                            errorMessage = getString(R.string.hour_not_available);
                                            break;
                                        case 404:
                                            errorMessage = getString(R.string.request_not_valid);
                                            break;
                                    }
                                }

                                String finalErrorMessage = errorMessage;
                                getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Utility.showToast(view, finalErrorMessage, Toast.LENGTH_SHORT);
                                    }
                                });
                            }).start()
                    );
                }
            }else {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}