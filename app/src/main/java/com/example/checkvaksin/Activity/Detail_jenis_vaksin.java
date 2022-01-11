package com.example.checkvaksin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkvaksin.R;
import com.squareup.picasso.Picasso;

public class Detail_jenis_vaksin extends AppCompatActivity {
    private int xid;
    private String xnama, xketerangan, xdosis, xjumlahsuntikan, xjaraksuntikan, xbatasumur, xjumlahvaksin, xgambar;
    private TextView tv_nama_vaksin, tv_keterangan, tv_dosis, tv_jumlah_suntikan, tv_jarak_suntikan, tv_batas_umur, tv_jumlah_vaksin;
    private ImageView im_detailgambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jenis_vaksin);

        Intent terima = getIntent();
        xid = terima.getIntExtra("xid", -1);
        xketerangan = terima.getStringExtra("xketerangan");
        xnama = terima.getStringExtra("xnama");
        xdosis = terima.getStringExtra("xdosis");
        xjumlahsuntikan = terima.getStringExtra("xjumlahsuntikan");
        xjaraksuntikan = terima.getStringExtra("xjaraksuntikan");
        xbatasumur = terima.getStringExtra("xbatasumur");
        xjumlahvaksin = terima.getStringExtra("xjumlahvaksin");
        xgambar = terima.getStringExtra("xgambar");

        tv_nama_vaksin = findViewById(R.id.tv_nama_vaksin);
        tv_keterangan = findViewById(R.id.tv_keterangan);
        tv_dosis = findViewById(R.id.tv_dosis);
        tv_jumlah_suntikan = findViewById(R.id.tv_jumlah_suntikan);
        tv_jarak_suntikan = findViewById(R.id.tv_jarak_suntikan);
        tv_batas_umur = findViewById(R.id.tv_batas_umur);
        tv_jumlah_vaksin = findViewById(R.id.tv_jumlah_vaksin);
        im_detailgambar = findViewById(R.id.im_gambardetail);

        tv_nama_vaksin.setText(xnama);
        tv_keterangan.setText(xketerangan);
        tv_dosis.setText(xdosis);
        tv_jumlah_suntikan.setText(xjumlahsuntikan);
        tv_jarak_suntikan.setText(xjaraksuntikan);
        tv_batas_umur.setText(xbatasumur);
        tv_jumlah_vaksin.setText(xjumlahvaksin);
        Picasso.get().load(xgambar).into(im_detailgambar);


    }
}