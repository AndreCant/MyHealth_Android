package it.mwt.myhealth.ui.doctor;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;

import java.util.List;

import it.mwt.myhealth.model.Doctor;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.volley.DoctorRequest;
import it.mwt.myhealth.volley.ExamRequest;

public class DoctorViewModel extends ViewModel {

    private MutableLiveData<List<Doctor>> data = new MutableLiveData<>();

    private MutableLiveData<Doctor> doctor = new MutableLiveData<>();

    public LiveData<List<Doctor>> getDoctorList() {
        return data;
    }

    public LiveData<Doctor> getSelectedDoctor() {
        return doctor;
    }

    public void setSelectedDoctor(Doctor doctor) {
        this.doctor.setValue(doctor);
    }

    public void retrieveData(Context context) {
        DoctorRequest.getInstance().getDoctors(
                context,
                response -> new Thread(() -> {
                    List<Doctor> doctors;
                    try {
                        doctors = ParseJSON.json2doctor(response);
                        data.postValue(doctors);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }).start(),
                error -> new Thread(() -> {
                    System.out.println("DoctorViewModel::retrieveData::ERROR::" + error.networkResponse.statusCode);
                }).start()
        );
    }
}
