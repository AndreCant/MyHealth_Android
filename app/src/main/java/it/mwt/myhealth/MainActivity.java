package it.mwt.myhealth;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import it.mwt.myhealth.ui.category.CategoriesFragment;
import it.mwt.myhealth.ui.profile.ProfileFragment;
import it.mwt.myhealth.ui.myExam.MyExamsActivity;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CategoriesFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    fragment = new CategoriesFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.exams:
                    Intent intent = new Intent(MainActivity.this, MyExamsActivity.class);
                    startActivity(intent);
                    return true;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,  fragment).commit();
            return true;
        }
    };
}