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

public class InputJenisVaksin extends AppCompatActivity {
    private EditText et_nama_vaksin, et_keterangan, et_dosis, et_jumlah_suntikan, et_jarak_suntikan, et_batas_umur, et_stock_vaksin, et_gambar;
    private Button btn_simpanvaksin;
    private String namavaksin, keterangan, dosis, jumlahsuntikan, jaraksuntikan, batasumur, stockvaksin, gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jenis_vaksin);

        et_nama_vaksin = findViewById(R.id.et_nama_vaksin);
        et_keterangan = findViewById(R.id.et_keterangan);
        et_dosis = findViewById(R.id.et_dosis);
        et_jumlah_suntikan = findViewById(R.id.et_jumlah_suntikan);
        et_jarak_suntikan = findViewById(R.id.et_jarak_suntikan);
        et_batas_umur = findViewById(R.id.et_batas_umur);
        et_stock_vaksin = findViewById(R.id.et_stock_vaksin);
        et_gambar = findViewById(R.id.et_gambar);
        btn_simpanvaksin = findViewById(R.id.btn_simpanvaksin);

        btn_simpanvaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namavaksin = et_nama_vaksin.getText().toString();
                keterangan = et_keterangan.getText().toString();
                dosis = et_dosis.getText().toString();
                jumlahsuntikan = et_jumlah_suntikan.getText().toString();
                jaraksuntikan = et_jarak_suntikan.getText().toString();
                batasumur = et_batas_umur.getText().toString();
                stockvaksin = et_stock_vaksin.getText().toString();
                gambar = et_gambar.getText().toString();

                if (namavaksin.trim().equals("")){
                    et_nama_vaksin.setError("tidak boleh kosong !");
                }else if (keterangan.trim().equals("")){
                    et_keterangan.setError("tidak boleh kosong !");
                }else if (dosis.trim().equals("")){
                    et_dosis.setError("tidak boleh kosong !");
                }else if (jumlahsuntikan.trim().equals("")){
                    et_jumlah_suntikan.setError("tidak boleh koson !");
                }else if (jaraksuntikan.trim().equals("")){
                    et_jarak_suntikan.setError("tidak boleh kosong !");
                }else if (batasumur.trim().equals("")){
                    et_jarak_suntikan.setError("tidak boleh kosong !");
                }else if (batasumur.trim().equals("")){
                    et_batas_umur.setError("tidak boleh kosong !");
                }else if (stockvaksin.trim().equals("")){
                    et_stock_vaksin.setError("tidak boleh kosong !");
                }else if (gambar.trim().equals("")){
                    et_gambar.setError("tidak boleh kosong !");
                }else {
                    createdatavaksin();
                }
            }
        });
    }

    private void createdatavaksin(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardcreatejenisvaksin(namavaksin, keterangan,dosis, jumlahsuntikan, jaraksuntikan,
                batasumur, stockvaksin, gambar);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InputJenisVaksin.this, "kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(InputJenisVaksin.this, "Gagal mengirim data ! | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}