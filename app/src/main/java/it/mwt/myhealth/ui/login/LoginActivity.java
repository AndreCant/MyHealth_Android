package it.mwt.myhealth.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.registration.RegistrationActivity;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.util.Utility;
import it.mwt.myhealth.volley.UserRequest;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = LoginActivity.class.getSimpleName();

    private EditText editTextusername;
    private EditText editTextPassword;
    private Button loginButton;
    private TextView registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        editTextusername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.cirLoginButton);
        registerButton = findViewById(R.id.toRegisterBtn);

        loginButton.setOnClickListener(view -> {
            String username = editTextusername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (username.isEmpty()) {
                editTextusername.setError(getString(R.string.field_empty));
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError(getString(R.string.field_empty));
                return;
            }

            JSONObject jsonRequest = ParseJSON.login2JSON(username, password);

            UserRequest.getInstance().login(LoginActivity.this,
                    jsonRequest,
                    response -> new Thread(() -> {
                        try {
                            Preferences.setUser(getApplicationContext(), ParseJSON.json2user(response));
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }).start(),
                    error -> new Thread(() -> {
                        String errorMessage = getString(R.string.service_not_available);
                        if(error.networkResponse != null){
                            switch ( error.networkResponse.statusCode){
                                case 401:
                                    errorMessage = getString(R.string.invalid_credentials);
                                    break;
                                case 403:
                                    errorMessage = getString(R.string.access_denied);
                                    break;
                                case 404:
                                    errorMessage = getString(R.string.request_not_valid);
                                    break;
                            }
                        }

                        String finalErrorMessage = errorMessage;
                        runOnUiThread(() -> {
                            Utility.showToast(view, finalErrorMessage, Toast.LENGTH_LONG);
                            editTextusername.setError(finalErrorMessage);
                            editTextPassword.setError(finalErrorMessage);
                        });
                    }).start()
            );
        });

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
