package it.mwt.myhealth.ui.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import it.mwt.myhealth.R;
import it.mwt.myhealth.util.LocationUtils;

public class ClinicLocationActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private Marker marker;

    private LocationCallback callback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);

            if(!locationResult.getLocations().isEmpty()) {
                Location location = locationResult.getLocations().get(0);
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

                if(marker == null) {
                    MarkerOptions options = new MarkerOptions();
                    options.title("MY LOCATION");
                    options.position(position);
                    marker = map.addMarker(options);
                } else {
                    marker.setPosition(position);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
        System.out.println("qui");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        System.out.println("qui");
        mapFragment.getMapAsync(this);

        setContentView(R.layout.activity_clinic_location);

        System.out.println("qui");
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textLocation = findViewById(R.id.textLocation);
        System.out.println("qui");

        System.out.println("qui");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng position = new LatLng(
                getIntent().getDoubleExtra("latitude", 0.0),
                getIntent().getDoubleExtra("longitude", 0.0));

        MarkerOptions options = new MarkerOptions();
        options.position(position);
        options.title(getIntent().getStringExtra("name"));
        map.addMarker(options);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f));

        if(!LocationUtils.startGoogleServices(getApplicationContext(), callback)) {
            ActivityCompat.requestPermissions(ClinicLocationActivity.this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocationUtils.stopGoogleServices(getApplicationContext(), callback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationUtils.start(getApplicationContext(), this);
            } else {
                // TODO:
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

        if(marker == null) {
            MarkerOptions options = new MarkerOptions();
            options.title("MY LOCATION");
            options.position(position);
            marker = map.addMarker(options);
        } else {
            marker.setPosition(position);
        }
    }
}