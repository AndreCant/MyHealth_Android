package it.mwt.myhealth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.database.DBSQL;
import it.mwt.myhealth.model.ClinicLocation;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottom_navigation_menu);

    }

//    private TextInputEditText loginUsername;
//    private TextInputEditText loginPassword;
//    private Button btnSignIn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        System.out.println("onCreate");
//
//        loginUsername = findViewById(R.id.loginUsername);
//        loginPassword = findViewById(R.id.loginPassword);
//        btnSignIn = findViewById(R.id.btnSignIn);
//
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = loginUsername.getText().toString();
//                if (username.isEmpty()) {
//                    loginUsername.setError(getString(R.string.field_empty));
//                    return;
//                }
//
//                String password = loginPassword.getText().toString();
//                if (password.isEmpty()) {
//                    loginPassword.setError(getString(R.string.field_empty));
//                    return;
//                }
//
//                List<ClinicLocation> listC = new ArrayList<>();
//                ClinicLocation c = new ClinicLocation();
//                c.setName("Test");
//                c.setLatitude(10.09);
//                c.setLongitude(90.88);
//                listC.add(c);
//                System.out.println(listC.get(0).getId());
//                System.out.println(listC.get(0).getName());
//                DBSQL.getInstance(getApplicationContext()).insert(listC);
//
//                List<ClinicLocation> listC2 = DBSQL.getInstance(getApplicationContext()).findAll();
//                System.out.println(listC2.size());
//                System.out.println(listC2.get(0));
//
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                intent.putExtra(MenuActivity.KEY_USERNAME, username);
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        System.out.println("onStart");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        System.out.println("onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        System.out.println("onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        System.out.println("onStop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        System.out.println("onDestroy");
//    }
}