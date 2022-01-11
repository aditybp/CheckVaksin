package com.example.checkvaksin.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkvaksin.R;

public class TentangAplikasi extends AppCompatActivity {
    String tentangaplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_aplikasi);

        tentangaplikasi = "Aplikasi Get Vaccinate merupakan aplikasi pencarian lokasi vaksinasi terdekat dimana pencarian berdasarkan" +
                " jarak lokasi terdekat dengan pengguna, aplikasi ini menggunakan metode haversine formula dalam penentuan" +
                " perhitungan jarak terdekat. Dan juga menggunakan Google Maps API sebagai media maping online.";

        TextView tentangAplikasi = (TextView)findViewById(R.id.tentangaplikasi);
        tentangAplikasi.setText(tentangaplikasi);
    }
}