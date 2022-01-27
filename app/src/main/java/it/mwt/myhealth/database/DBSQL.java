package it.mwt.myhealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.mwt.myhealth.model.ClinicLocation;

public class DBSQL extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database_sql";

    private final static int VERSION = 1;

    private static volatile DBSQL instance = null;

    public static synchronized DBSQL getInstance(Context context) {

        if(instance == null) {
            synchronized (DBSQL.class) {
                if(instance == null) instance = new DBSQL(context);
            }
        }
        return instance;
    }

    private DBSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE clinic_location (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "latitude NUMBER," +
                "longitude NUMBER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void insert(List<ClinicLocation> data) {

        for(ClinicLocation clinicLocation : data) {
            ContentValues values = new ContentValues();
            values.put("name", clinicLocation.getName());
            values.put("latitude", clinicLocation.getLatitude());
            values.put("longitude", clinicLocation.getLongitude());

            long id = getWritableDatabase().insert("clinic_location", null, values);
            System.out.println(id);
            if(id != -1) clinicLocation.setId(id);
        }
    }

    public List<ClinicLocation> findAll() {
        List<ClinicLocation> data = new ArrayList<>();

        String sql = "SELECT * FROM clinic_location ORDER BY name ASC";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while(cursor.moveToNext()) {
            ClinicLocation clinicLocation = new ClinicLocation();
            clinicLocation.setId(cursor.getLong(cursor.getColumnIndex("id")));
            clinicLocation.setName(cursor.getString(cursor.getColumnIndex("name")));
            clinicLocation.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
            clinicLocation.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));

            data.add(clinicLocation);
        }
        cursor.close();

        return data;
    }
}
