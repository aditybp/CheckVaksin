package com.example.checkvaksin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Model.ResponseModel;
import com.example.checkvaksin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputDataLokasiVaksin extends AppCompatActivity {
    private EditText et_namatempat, et_latitude, et_longitude, et_alamat, et_notelepon;
    private Button btn_simpanlokasi;
    private String namatempat, latitude, longitude, alamat, notelepon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_lokasi_vaksin);

        et_namatempat = findViewById(R.id.et_namatempat);
        et_latitude = findViewById(R.id.et_latitude);
        et_longitude = findViewById(R.id.et_longitude);
        et_alamat = findViewById(R.id.et_alamat);
        et_notelepon = findViewById(R.id.et_notelepon);
        btn_simpanlokasi = findViewById(R.id.btn_simpanlokasi);

        btn_simpanlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namatempat = et_namatempat.getText().toString();
                latitude = et_latitude.getText().toString();
                longitude = et_longitude.getText().toString();
                alamat = et_alamat.getText().toString();
                notelepon = et_notelepon.getText().toString();

                if (namatempat.trim().equals("")){
                    et_namatempat.setError("tidak boleh kosong !");
                }else if (latitude.trim().equals("")){
                    et_latitude.setError("tidak boleh kosong !");
                }else if (longitude.trim().equals("")){
                    et_longitude.setError("tidak boleh kosong !");
                }else if (alamat.trim().equals("")){
                    et_alamat.setError("tidak boleh kosong !");
                }else if (notelepon.trim().equals("")){
                    et_notelepon.setError("tidak boleh kosong !");
                }else {
                    createdatalokasi();
                }
            }
        });
    }

    private void createdatalokasi(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanLokasi = ardData.ardcreatedatalokasi(namatempat, latitude, longitude, alamat,notelepon);

        simpanLokasi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InputDataLokasiVaksin.this, "kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(InputDataLokasiVaksin.this, "Gagal mengirim data ! | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}