package com.example.checkvaksin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkvaksin.R;

public class AdminMenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_utama);
    }

    public void btn_inputjenisvaksin(View view) {
        Intent intent = new Intent(AdminMenuUtama.this,InputJenisVaksin.class);
        startActivity(intent);
    }

    public void btn_inputdatalokasivaksin(View view) {
        Intent intent = new Intent(AdminMenuUtama.this,InputDataLokasiVaksin.class);
        startActivity(intent);
    }
}