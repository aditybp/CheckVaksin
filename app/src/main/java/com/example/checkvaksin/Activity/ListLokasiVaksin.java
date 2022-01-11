package com.example.checkvaksin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Adapter.AdapterLokasi;
import com.example.checkvaksin.Model.DataModel;
import com.example.checkvaksin.Model.DataModelLokasi;
import com.example.checkvaksin.Model.ResponseModelLokasi;
import com.example.checkvaksin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLokasiVaksin extends AppCompatActivity {
    private RecyclerView rv_data_lokasi;
    private RecyclerView.Adapter adDatalokasi;
    private RecyclerView.LayoutManager lmDatalokasi;
    private List<DataModelLokasi> listlokasi = new ArrayList<>();
    //private List<DataModelLokasi> listdatalokasi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lokasi_vaksin);

        rv_data_lokasi = findViewById(R.id.rv_data_lokasi);
        lmDatalokasi= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_data_lokasi.setLayoutManager(lmDatalokasi);
        RetriveLokasi();
    }

    public void RetriveLokasi (){
        APIRequestData ardLokasi = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelLokasi> tampillokasi = ardLokasi.ardRetriveLokasi();

        tampillokasi.enqueue(new Callback<ResponseModelLokasi>() {
            @Override
            public void onResponse(Call<ResponseModelLokasi> call, Response<ResponseModelLokasi> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ListLokasiVaksin.this, "kode :"+kode+"pesan :"+pesan, Toast.LENGTH_SHORT).show();

                listlokasi = response.body().getData();

                adDatalokasi = new AdapterLokasi(ListLokasiVaksin.this, listlokasi);
                rv_data_lokasi.setAdapter(adDatalokasi);
                adDatalokasi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelLokasi> call, Throwable t) {
                Toast.makeText(ListLokasiVaksin.this, "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}