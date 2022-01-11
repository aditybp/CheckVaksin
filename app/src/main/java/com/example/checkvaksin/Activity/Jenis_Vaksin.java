package com.example.checkvaksin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Adapter.AdapterData;
import com.example.checkvaksin.Model.DataModel;
import com.example.checkvaksin.Model.ResponseModel;
import com.example.checkvaksin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Jenis_Vaksin extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_vaksin);

        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retriveData();
    }

    public void retriveData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetriveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(Jenis_Vaksin.this, "Kode :"+ kode+"Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adData = new AdapterData(Jenis_Vaksin.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(Jenis_Vaksin.this, "Gagal menghubungi server :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}