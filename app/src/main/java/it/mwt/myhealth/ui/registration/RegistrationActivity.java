package it.mwt.myhealth.ui.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Objects;

import it.mwt.myhealth.MainActivity;
import it.mwt.myhealth.R;
import it.mwt.myhealth.ui.login.LoginActivity;
import it.mwt.myhealth.util.ParseJSON;
import it.mwt.myhealth.util.Preferences;
import it.mwt.myhealth.util.Utility;
import it.mwt.myhealth.volley.UserRequest;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextFiscalCode;
    private EditText editTextName;
    private EditText editTextSurname;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button birthdayButton;
    private TextView birthdayTextView;
    private Button registerButton;
    private TextView loginButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.usernameRegistration);
        editTextPassword = findViewById(R.id.passwordRegistration);
        editTextEmail = findViewById(R.id.emailRegistration);
        editTextFiscalCode = findViewById(R.id.fiscalCodeRegistration);
        editTextName = findViewById(R.id.nameRegistration);
        editTextSurname = findViewById(R.id.surnameRegistration);
        radioMale = findViewById(R.id.male);
        radioFemale = findViewById(R.id.female);
        birthdayButton = findViewById(R.id.selectBirthdayRegistration);
        birthdayTextView = findViewById(R.id.showBirthdayRegistration);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.registerBtn);

        birthdayTextView.setText(R.string.select_date);

        registerButton.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String email = editTextEmail.getText().toString();
            String fiscalCode = editTextFiscalCode.getText().toString();
            String name = editTextName.getText().toString();
            String surname = editTextSurname.getText().toString();
            String gender = radioMale.isChecked() ? "M" : (radioFemale.isChecked() ? "F" : null );

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

            JSONObject jsonRequest = ParseJSON.register2JSON(username, email, password, fiscalCode, name, surname, gender, date);

            UserRequest.getInstance().registration(RegistrationActivity.this,
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
                        try {
                            JSONObject data = new JSONObject(new String(error.networkResponse.data, "utf-8"));
                            System.out.println(data);
                            if(error.networkResponse != null){
                                switch ( error.networkResponse.statusCode){
                                    case 400:
                                        errorMessage = data.getString("constraint");
                                        break;
                                    case 401:
                                        errorMessage = getString(R.string.alredy_exist);
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
                                if (finalErrorMessage.contains("fcode")){
                                    Utility.showToast(view, getString(R.string.invalid_fiscal_code), Toast.LENGTH_LONG);
                                    editTextFiscalCode.setError(getString(R.string.invalid_fiscal_code));
                                }else{
                                    Utility.showToast(view, finalErrorMessage, Toast.LENGTH_LONG);
                                    editTextUsername.setError(finalErrorMessage);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }).start()
            );
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        birthdayButton.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    RegistrationActivity.this,
                    R.style.datepicker,
                    mDateSetListener,
                    year,month,day);
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
                birthdayTextView.setText(dateToShow);
            }
        };
    }
}
