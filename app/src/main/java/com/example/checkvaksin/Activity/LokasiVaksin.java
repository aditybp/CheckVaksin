package com.example.checkvaksin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Adapter.AdapterDetailLokasi;
import com.example.checkvaksin.Model.DataModelDetailLokasi;
import com.example.checkvaksin.Model.ResponseModel;
import com.example.checkvaksin.Model.ResponseModelDetailLokasi;
import com.example.checkvaksin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LokasiVaksin extends AppCompatActivity {
    String id_lokasi = AdapterDetailLokasi.mMyAppsBundle.getString("xid_lokasi");
    int id_lokasi_int = Integer.parseInt(id_lokasi);
    private RecyclerView rv_data_detail_lokasi;
    private RecyclerView.Adapter adDataDetail;
    private RecyclerView.LayoutManager lmDataDetail;
    private List<DataModelDetailLokasi> listDataDetail = new ArrayList<>();
    private int xid;
    private String xnamatempat;
    private String xalamat;
    private String xtelepon;
    private double xlatitude, xlongitude;
    private TextView tv_nama_lokasi, tv_alamatlokasi, tv_notelepon, tv_latitudelokasi, tv_longitudelokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_vaksin);

        rv_data_detail_lokasi = findViewById(R.id.rv_data_detail_lokasi);
        lmDataDetail = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_data_detail_lokasi.setLayoutManager(lmDataDetail);
        RetriveDetailLokasi();

        Intent terima = getIntent();
        xid = terima.getIntExtra("xid", -1);
        xnamatempat = terima.getStringExtra("xnamatempat");
        xlatitude = terima.getDoubleExtra("xlatitude", 0);
        xlongitude = terima.getDoubleExtra("xlongitude", 0);
        xalamat = terima.getStringExtra("xalamat");
        xtelepon = terima.getStringExtra("xtelepon");

        tv_nama_lokasi = findViewById(R.id.tv_nama_lokasi);
        tv_latitudelokasi = findViewById(R.id.tv_latitudelokasi);
        tv_longitudelokasi = findViewById(R.id.tv_longitudelokasi);
        tv_alamatlokasi = findViewById(R.id.tv_alamatlokasi);
        tv_notelepon = findViewById(R.id.tv_notelepon);

        tv_nama_lokasi.setText(xnamatempat);
        tv_latitudelokasi.setText(Double.toString(xlatitude));
        tv_longitudelokasi.setText(Double.toString(xlongitude));
        tv_alamatlokasi.setText(xalamat);
        tv_notelepon.setText(xtelepon);
    }

    public void RetriveDetailLokasi(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelDetailLokasi> ambildata = ardData.ardGetDetaillokasi(id_lokasi_int);

        ambildata.enqueue(new Callback<ResponseModelDetailLokasi>() {
            @Override
            public void onResponse(Call<ResponseModelDetailLokasi> call, Response<ResponseModelDetailLokasi> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listDataDetail = response.body().getData();

                adDataDetail = new AdapterDetailLokasi(LokasiVaksin.this, listDataDetail);
                rv_data_detail_lokasi.setAdapter(adDataDetail);
                adDataDetail.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelDetailLokasi> call, Throwable t) {
                Toast.makeText(LokasiVaksin.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_mapslokasi(View view) {

        Intent intent = new Intent(LokasiVaksin.this, MapsActivity.class);
        intent.putExtra("xnama", xnamatempat);
        intent.putExtra("xlatitude", xlatitude);
        intent.putExtra("xlongitude", xlongitude);
        startActivity(intent);
    }
}