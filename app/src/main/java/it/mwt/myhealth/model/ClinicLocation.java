package it.mwt.myhealth.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clinic_location")
public class ClinicLocation implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    private double latitude;

    private double longitude;

    public static final Creator<ClinicLocation> CREATOR = new Creator<ClinicLocation>() {
        @Override
        public ClinicLocation createFromParcel(Parcel in) {
            return new ClinicLocation(in);
        }

        @Override
        public ClinicLocation[] newArray(int size) {
            return new ClinicLocation[size];
        }
    };

    public ClinicLocation(){}

    public ClinicLocation(Parcel in){
        setId(in.readLong());
        setName(in.readString());
        setLatitude(in.readDouble());
        setLongitude(in.readDouble());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
