package it.mwt.myhealth.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.model.User;
import it.mwt.myhealth.ui.categories.CategoriesFragment;
import it.mwt.myhealth.ui.location.ClinicLocationActivity;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.UserRequest;

public class ProfileFragment extends Fragment {

    private TextView usernameText;
    private TextView emailText;
    private TextView nameText;
    private TextView surnameText;
    private TextView fiscalCodeText;
    private TextView dateOfBirthText;
    private TextView genderText;
    private TextView logout;
    private Button location;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Bind view
        usernameText = view.findViewById(R.id.profile_text_username);
        emailText = view.findViewById(R.id.profile_text_email);
        nameText = view.findViewById(R.id.profile_text_name);
        surnameText = view.findViewById(R.id.profile_text_surname);
        fiscalCodeText = view.findViewById(R.id.profile_text_fiscal_code);
        dateOfBirthText = view.findViewById(R.id.profile_text_date_of_birth);
        genderText = view.findViewById(R.id.profile_text_gender);
        logout = view.findViewById(R.id.profile_logout);
        location = view.findViewById(R.id.clinic_location_btn);

        if (!Preferences.isLogged(getContext())) {
            getParentFragmentManager().beginTransaction().replace(R.id.frame_layout,  new CategoriesFragment()).commit();
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }else {
            setProfileInfo();
            setLogoutBtn();
            setLocationBtn();
        }
    }

    private void setProfileInfo(){

        if (Preferences.getId(getContext()) == 0){
            UserRequest.getInstance().profile(
                    getContext(),
                    null,
                    response -> new Thread(() -> {
                        try {
                            User user = ParseJSON.json2user(response);
                            Preferences.setUserInfo(getContext(), user);
                            setTextView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }).start(),
                    error -> new Thread(() -> {
                        if(error.networkResponse.statusCode == 401){
                            Preferences.setUser(getContext(), null);
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }).start()
            );
        }else {
            setTextView();
        }
    }

    private void setTextView(){
        usernameText.setText(Preferences.getUsername(getContext()));
        emailText.setText(Preferences.getEmail(getContext()));
        nameText.setText(Preferences.getName(getContext()));
        surnameText.setText(Preferences.getSurname(getContext()));
        fiscalCodeText.setText(Preferences.getFiscalCode(getContext()));
        dateOfBirthText.setText(Preferences.getDateOfBirth(getContext()));
        genderText.setText(Preferences.getGender(getContext()));
    }

    private void setLogoutBtn(){
        logout.setOnClickListener(view -> {
            Preferences.setUser(getContext(), null);
            getParentFragmentManager().beginTransaction().replace(R.id.frame_layout,  new CategoriesFragment()).commit();
        });
    }

    private void setLocationBtn(){
        location.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ClinicLocationActivity.class);
            startActivity(intent);
        });
    }
}