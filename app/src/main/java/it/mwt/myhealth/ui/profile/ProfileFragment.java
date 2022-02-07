package it.mwt.myhealth.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.model.User;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.UserRequest;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Fragment");
        if (!Preferences.isLogged(getContext())) {
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }else {
            setProfileInfo();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void setProfileInfo(){

        if (Preferences.getId(getContext()) == 0){
            System.out.println("No user set!");
            UserRequest.getInstance().profile(
                    getContext(),
                    null,
                    response -> {
                        try {
                            System.out.println("success");
                            User user = ParseJSON.json2user(response);
                            System.out.println(user.getId());
                            System.out.println(user.getUsername());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        System.out.println("error");

                        if(error.networkResponse.statusCode == 401){
                            Preferences.setUser(getContext(), null);
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
        }else {
            System.out.println("User already setted!");

            System.out.println(Preferences.getId(getContext()));
        }

    }
}