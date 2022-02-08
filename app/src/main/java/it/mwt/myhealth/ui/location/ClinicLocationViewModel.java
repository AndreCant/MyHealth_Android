package it.mwt.myhealth.ui.location;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.database.DBSQL;
import it.mwt.myhealth.model.ClinicLocation;
import it.mwt.myhealth.util.Preferences;

public class ClinicLocationViewModel extends ViewModel {

    private MutableLiveData<ClinicLocation> location = new MutableLiveData<>();

    public LiveData<ClinicLocation> getClinicLocation(){
        return location;
    }

    public void retrieveData(Context context) {
        if(Preferences.getFirstStartLocation(context)) {
            new Thread(() -> {
                List<ClinicLocation> locations = new ArrayList<>();
                ClinicLocation location = new ClinicLocation();
                location.setName("MyHealth Clinic");
                location.setLatitude(41.890198);
                location.setLongitude(12.492502);
                locations.add(location);

                DBSQL.getInstance(context).insert(locations);
                this.location.postValue(location);

                Preferences.setFirstStartLocation(context, false);
            }).start();
        } else {
            new Thread(() -> {
                this.location.postValue(DBSQL.getInstance(context).findLastClinicLocation());
            }).start();
        }
    }
}
