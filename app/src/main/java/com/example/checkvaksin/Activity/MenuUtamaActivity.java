package com.example.checkvaksin.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.checkvaksin.R;

public class MenuUtamaActivity extends AppCompatActivity {
    private Button btn_lokasi;
    TextView tvlatitude, tvlongitude;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private Context ctx;
    public static Bundle mMyAppsBundle = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        btn_lokasi = findViewById(R.id.btn_lokasi);
        tvlatitude = findViewById(R.id.tv_latitude);
        tvlongitude = findViewById(R.id.tv_longitude);

        //sharedPreferences = getSharedPreferences("xlatitude", MODE_PRIVATE);
        //sharedPreferences.contains("");


        btn_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                } else {
                    getLocation();
                }

            }
        });

    }

    public double getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MenuUtamaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MenuUtamaActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuUtamaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (locationGPS != null){
                double lati = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();


                TextView tvlatitude = (TextView)findViewById(R.id.tv_latitude);
                TextView tvlongitude = (TextView)findViewById(R.id.tv_longitude);

                tvlatitude.setText(String.valueOf(lati));
                tvlongitude.setText(String.valueOf(longi));
                //double kalkulasi = kalkulasi(targetlatitude, targetlongitude, latitude,longitude);
                //tvalamat.setText(String.valueOf(kalkulasi));

                MenuUtamaActivity.mMyAppsBundle.putString("xlatitude", String.valueOf(lati));
                MenuUtamaActivity.mMyAppsBundle.putString("xlongitude", String.valueOf(longi));

                Toast.makeText(this, "latitude : "+lati+" "+" longitude : "+longi, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "gagal mendapatkan loaksi", Toast.LENGTH_SHORT).show();
            }

        }
        return 0;
    }

    public void btn_pindahdetailvaksin(View view) {
        Intent kirim = new Intent(MenuUtamaActivity.this,Jenis_Vaksin.class);
        startActivity(kirim);
    }


    public void btn_pindahlokasi(View view) {
        Intent kirim = new Intent(MenuUtamaActivity.this,ListLokasiVaksin.class);
        startActivity(kirim);
    }


    public void btn_lokasiall(View view) {
        Intent kirim = new Intent(MenuUtamaActivity.this,MapsActivity.class);
        startActivity(kirim);
    }

    public void btn_tentangAplikasi(View view) {
        Intent kirim = new Intent(MenuUtamaActivity.this,TentangAplikasi.class);
        startActivity(kirim);
    }
}