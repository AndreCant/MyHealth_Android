package it.mwt.myhealth.ui.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

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
import it.mwt.myhealth.databinding.ActivityClinicLocationBinding;
import it.mwt.myhealth.model.ClinicLocation;
import it.mwt.myhealth.util.LocationUtils;

public class ClinicLocationActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Marker marker;
    private ActivityClinicLocationBinding binding;
    private ClinicLocationViewModel clinicLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clinicLocation = new ViewModelProvider(this).get(ClinicLocationViewModel.class);
        clinicLocation.retrieveData(getApplicationContext());

        binding = ActivityClinicLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        clinicLocation.getClinicLocation().observe(this, location -> {
            if(location != null) {
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

                MarkerOptions options = new MarkerOptions();
                options.position(position);
                options.title(location.getName());
                mMap.addMarker(options);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f));
            }
        });

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
                LocationUtils.stop(getApplicationContext(), this);
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
            marker = mMap.addMarker(options);
        } else {
            marker.setPosition(position);
        }
    }

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
                    marker = mMap.addMarker(options);
                } else {
                    marker.setPosition(position);
                }
            }
        }
    };
}