package it.mwt.myhealth;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView textUsername = findViewById(R.id.textUsername);

        String username = getIntent().getStringExtra(KEY_USERNAME);
        if (username != null) textUsername.setText(username);
    }
}
