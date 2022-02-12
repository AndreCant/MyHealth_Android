package it.mwt.myhealth.ui.exams;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;

import java.util.List;

import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.volley.ExamRequest;

public class ExamViewModel extends ViewModel {

    private MutableLiveData<List<Exam>> data = new MutableLiveData<>();

    private MutableLiveData<Exam> exam = new MutableLiveData<>();

    private String type;

    public LiveData<List<Exam>> getExamList() {
        return data;
    }

    public LiveData<Exam> getSelectedExam() {
        return exam;
    }

    public void setSelectedExam(Exam exam) {
        this.exam.setValue(exam);
    }

    public void setType(String type) { this.type = type; }

    public void retrieveData(Context context) {
        switch (this.type){
            case "all" :
                ExamRequest.getInstance().getExams(
                    context,
                    null,
                    response -> new Thread(() -> {
                        List<Exam> exams;

                        try {
                            exams = ParseJSON.json2exam(response);
                            data.postValue(exams);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }).start(),
                    error -> new Thread(() -> {

                    }).start()
                );
            break;

            case "exam":
            break;

            case "path":
            break;
        }


    }
}
