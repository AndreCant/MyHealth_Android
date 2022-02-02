package it.mwt.myhealth.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.volley.UserRequest;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextFiscalCode;
    private Button registerButton;
    private TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_register);

        //Bind view
        editTextUsername = findViewById(R.id.usernameRegistration);
        editTextPassword = findViewById(R.id.passwordRegistration);
        editTextEmail = findViewById(R.id.emailRegistration);
        editTextFiscalCode = findViewById(R.id.fiscalCodeRegistration);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.registerBtn);

        //On submit send login request
        registerButton.setOnClickListener(view -> {

            //Get values
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String email = editTextEmail.getText().toString();
            String fiscalCode = editTextFiscalCode.getText().toString();

            if (username.isEmpty()) {
                editTextUsername.setError(getString(R.string.field_empty));
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError(getString(R.string.field_empty));
                return;
            }

            if (email.isEmpty()) {
                editTextEmail.setError(getString(R.string.field_empty));
                return;
            }

            if (fiscalCode.isEmpty()) {
                editTextFiscalCode.setError(getString(R.string.field_empty));
                return;
            }

            //Parse username and password to JSON
            JSONObject jsonRequest = ParseJSON.register2JSON(username, email, password, fiscalCode);

            //Send login request
            UserRequest.getInstance().registration(RegistrationActivity.this,
                    jsonRequest,
                    responseListener,
                    error -> {
                        String errorMessage = "Servizio non disponibile";
                        try {
                            JSONObject data = new JSONObject(new String(error.networkResponse.data, "utf-8"));
                            System.out.println(data);
                            if(error.networkResponse != null){
                                switch ( error.networkResponse.statusCode){
                                    case 400:
                                        errorMessage = data.getString("constraint");
                                        break;
                                    case 401:
                                        errorMessage = "Username, Email or Fiscal Code alredy exist!";
                                        break;
                                    case 403:
                                        errorMessage = "Accesso negato";
                                        break;
                                    case 404:
                                        errorMessage = "Richiesta non valida";
                                        break;
                                }
                            }
                            if (errorMessage.contains("email")){
                                editTextEmail.setError(errorMessage);
                            }else{
                                if (errorMessage.contains("fcode")){
                                    editTextFiscalCode.setError("Invalid Fiscal Code");
                                }else{
                                    editTextUsername.setError(errorMessage);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
            );
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private final Response.Listener<JSONObject> responseListener = response -> {

        try {
            Preferences.setUser(getApplicationContext(), ParseJSON.json2user(response));

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

}
