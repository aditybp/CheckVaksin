package com.example.checkvaksin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkvaksin.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_pindahmenuutama(View view) {
        Intent intent = new Intent(MainActivity.this,MenuUtamaActivity.class);
        startActivity(intent);
    }

    public void btn_pindahadmin(View view) {
        Intent intent = new Intent(MainActivity.this,AdminMenuUtama.class);
        startActivity(intent);
    }
}