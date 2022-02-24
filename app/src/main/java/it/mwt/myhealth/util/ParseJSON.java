package it.mwt.myhealth.util;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.mwt.myhealth.model.Doctor;
import it.mwt.myhealth.model.Exam;
import it.mwt.myhealth.model.Reservation;
import it.mwt.myhealth.model.User;

public class ParseJSON {

    private static final String TAG = ParseJSON.class.getSimpleName();

    public static JSONObject login2JSON(String username, String password) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username",username);
            jsonRequest.put("password",password);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }

        return jsonRequest;
    }

    @SuppressLint("NewApi")
    public static User json2user(JSONObject response) throws JSONException {
        JSONObject jsonUser = response.has("user") ? response.getJSONObject("user") : response;
        User user = new User();

        if (jsonUser.has("id")) user.setId(jsonUser.getLong("id"));
        if (jsonUser.has("token")) user.setToken(jsonUser.getString("token"));
        if (jsonUser.has("username")) user.setUsername(jsonUser.getString("username"));
        if (jsonUser.has("email")) user.setEmail(jsonUser.getString("email"));
        if (jsonUser.has("name")) user.setName(jsonUser.getString("name"));
        if (jsonUser.has("surname")) user.setSurname(jsonUser.getString("surname"));
        if (jsonUser.has("fiscalCode")) user.setFiscalCode(jsonUser.getString("fiscalCode"));
        if (jsonUser.has("dateOfBirth")) user.setDateOfBirth(jsonUser.getString("dateOfBirth"));
        if (jsonUser.has("gender")) user.setGender(jsonUser.getString("gender"));

        return user;
    }

    public static JSONObject register2JSON(String username, String email, String password, String fiscalCode, String name, String surname, String gender, String dateOfBirth) {
        JSONObject jsonUser = new JSONObject();
        try {
            if(username != null) jsonUser.put("username", username);
            if(email != null) jsonUser.put("email", email);
            if(password != null) jsonUser.put("password", password);
            if(fiscalCode != null) jsonUser.put("fiscalCode", fiscalCode);
            if(name != null) jsonUser.put("name", name);
            if(surname != null) jsonUser.put("surname", surname);
            if(gender != null) jsonUser.put("gender", gender);
            if(dateOfBirth != null) jsonUser.put("dateOfBirth", dateOfBirth);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }

        return jsonUser;
    }

    public static ArrayList<Exam> json2exam(JSONObject response) throws JSONException {
        JSONArray jsonExams = response.has("all") ? response.getJSONArray("all") : null;
        ArrayList<Exam> exams = new  ArrayList();
        for (int i = 0; i < jsonExams.length(); i++) {
            JSONObject row = jsonExams.getJSONObject(i);
            Exam exam = new Exam();

            if (row.has("id")) exam.setId(row.getLong("id"));
            if (row.has("name")) exam.setName(row.getString("name"));
            if (row.has("price")) exam.setPrice(row.getDouble("price"));
            if (row.has("type")) exam.setType(row.getString("type"));
            if (row.has("specialization")) exam.setSpecialization(row.getString("specialization"));
            if (row.has("subSpecialization")) exam.setSubSpecialization(row.getString("subSpecialization"));
            if (row.has("description")) exam.setDescription(row.getString("description"));
            if (row.has("images")) {
                JSONArray images = row.getJSONArray("images");
                exam.setImageUrl(images.getJSONObject(0).getString("url"));
            }
            exams.add(exam);
        }
        return exams;
    }

    public static ArrayList<Exam> json2examsType(JSONObject response) throws JSONException {
        JSONArray jsonExams = response.has("exams") ? response.getJSONArray("exams") : response.getJSONArray("rehabilitationPaths");
        ArrayList<Exam> exams = new  ArrayList();
        for (int i = 0; i < jsonExams.length(); i++) {
            JSONObject row = jsonExams.getJSONObject(i);
            Exam exam = new Exam();

            if (row.has("id")) exam.setId(row.getLong("id"));
            if (row.has("name")) exam.setName(row.getString("name"));
            if (row.has("price")) exam.setPrice(row.getDouble("price"));
            if (row.has("type")) exam.setType(row.getString("type"));
            if (row.has("specialization")) exam.setSpecialization(row.getString("specialization"));
            if (row.has("subSpecialization")) exam.setSubSpecialization(row.getString("subSpecialization"));
            if (row.has("description")) exam.setDescription(row.getString("description"));
            if (row.has("images")) {
                JSONArray images = row.getJSONArray("images");
                exam.setImageUrl(images.getJSONObject(0).getString("url"));
            }
            exams.add(exam);
        }
        return exams;
    }

    public static ArrayList<Reservation> json2reservationsType(JSONObject response) throws JSONException {
        JSONArray jsonExams = response.has("reservations") ? response.getJSONArray("reservations") : null;
        ArrayList<Reservation> reservations = new  ArrayList();
        for (int i = 0; i < jsonExams.length(); i++) {
            JSONObject row = jsonExams.getJSONObject(i);
            Reservation reservation = new Reservation();
            String[] parts = row.getString("startHour").split("T");
            String final_start_hour= parts[1];
            String[] parts_reservation = row.getString("reservationDate").split("-");
            String final_date = parts_reservation[2]+"/"+parts_reservation[1]+"/"+parts_reservation[0];
            if (row.has("examImageUrl")) reservation.setImageUrl(row.getString("examImageUrl"));
            if (row.has("examName")) reservation.setExam(row.getString("examName"));
            if (row.has("endHour")) reservation.setEndHour(row.getString("endHour"));
            if (row.has("startHour")) reservation.setStartHour(final_start_hour);
            if (row.has("reservationDate")) reservation.setReservationDate(final_date);
            reservations.add(reservation);
        }
        return reservations;
    }

    public static List<Doctor> json2doctor(JSONObject response) throws JSONException {
        JSONArray jsonDoctors = response.has("doctors") ? response.getJSONArray("doctors") : null;
        List<Doctor> doctors = new  ArrayList();

        if (jsonDoctors != null){
            for (int i = 0; i < jsonDoctors.length(); i++) {
                JSONObject row = jsonDoctors.getJSONObject(i);
                Doctor doctor = new Doctor();

                if (row.has("id")) doctor.setId(row.getLong("id"));
                if (row.has("name")) doctor.setName(row.getString("name"));
                if (row.has("surname")) doctor.setSurname(row.getString("surname"));
                if (row.has("age")) doctor.setAge(row.getInt("age"));
                if (row.has("specialization")) doctor.setSpecialization(row.getString("specialization"));
                if (row.has("gender")) doctor.setGender(row.getString("gender"));
                if (row.has("urlImage")) doctor.setImageUrl(row.getString("urlImage"));
                if (row.has("skills")) doctor.setSkills(row.getString("skills"));
                doctors.add(doctor);
            }
        }
        return doctors;
    }
}
