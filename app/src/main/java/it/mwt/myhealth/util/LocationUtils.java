package it.mwt.myhealth.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;

import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;

public class LocationUtils {

    public static boolean start(Context context, LocationListener listener){

        int permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permission == PackageManager.PERMISSION_GRANTED) {
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            return true;
        }
        return false;
    }

    public static void stop(Context context, LocationListener listener){

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        manager.removeUpdates(listener);
    }

    public static boolean startGoogleServices(Context context, LocationCallback callback) {

        int permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permission == PackageManager.PERMISSION_GRANTED) {

            LocationRequest request = LocationRequest.create();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            request.setNumUpdates(10);
            request.setInterval(0);

            FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
            client.requestLocationUpdates(request, callback, Looper.myLooper());

            return true;
        }
        return false;
    }

    public static void stopGoogleServices(Context context, LocationCallback callback) {

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        client.removeLocationUpdates(callback);
    }
}
