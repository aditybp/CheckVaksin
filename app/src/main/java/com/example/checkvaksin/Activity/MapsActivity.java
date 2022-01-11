package com.example.checkvaksin.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Model.DataModelLokasi;
import com.example.checkvaksin.Model.ResponseModelLokasi;
import com.example.checkvaksin.R;
import com.example.checkvaksin.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Double xlatitude, xlongitude;
    private String xnama;
    private List<DataModelLokasi> mListLokasi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent terima = getIntent();

        xnama = terima.getStringExtra("xnama");
        xlatitude = terima.getDoubleExtra("xlatitude",0);
        xlongitude = terima.getDoubleExtra("xlongitude",0);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lokasi = new LatLng(xlatitude, xlongitude);
        mMap.addMarker(new MarkerOptions().position(lokasi).title(xnama));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi,14));
        
        getAllDataLokasi();
    }

    private void getAllDataLokasi() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelLokasi> tampilData = ardData.ardRetriveLokasi();
        tampilData.enqueue(new Callback<ResponseModelLokasi>() {
            @Override
            public void onResponse(Call<ResponseModelLokasi> call, Response<ResponseModelLokasi> response) {
                mListLokasi = response.body().getData();
                initMarker(mListLokasi);
            }

            @Override
            public void onFailure(Call<ResponseModelLokasi> call, Throwable t) {

            }
        });
        
    }

    private void initMarker(List<DataModelLokasi> mListLokasi) {
        for (int i = 0; i < mListLokasi.size(); i++) {
            //set latlng nya
            LatLng location = new LatLng((mListLokasi.get(i).getLatitude()), (mListLokasi.get(i).getLongitude()));
            //tambahkan markernya
            mMap.addMarker(new MarkerOptions().position(location).title(mListLokasi.get(i).getNama_tempat()));
            //set latlng index ke 0
            LatLng latLng = new LatLng((mListLokasi.get(0).getLatitude()), (mListLokasi.get(0).getLongitude()));
            //lalu arahkan zooming ke marker index ke 0
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude), 11.0f));

        }
    }
}