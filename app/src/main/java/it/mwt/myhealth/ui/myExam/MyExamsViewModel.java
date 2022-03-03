package it.mwt.myhealth.ui.myExam;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;

import java.util.List;

import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.model.Reservation;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.volley.ExamRequest;
import it.mwt.myhealth.volley.ReservationRequest;

public class MyExamsViewModel extends ViewModel {

    private MutableLiveData<List<Reservation>> data = new MutableLiveData<>();

    private String type;

    public LiveData<List<Reservation>> getReservationsList() {
        return data;
    }

    public void setType(String type) { this.type = type; }

    public void retrieveData(Context context) {
        ReservationRequest.getInstance().getReservations(
                context,
                null,
                response -> new Thread(() -> {
                    List<Reservation> reservations;

                    try {
                        reservations = ParseJSON.json2reservationsType(response);
                        data.postValue(reservations);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }).start(),
                error -> new Thread(() -> {
                }).start()
        );
    }
}
