package com.example.checkvaksin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkvaksin.API.APIRequestData;
import com.example.checkvaksin.API.RetroServer;
import com.example.checkvaksin.Activity.LokasiVaksin;
import com.example.checkvaksin.Activity.MenuUtamaActivity;
import com.example.checkvaksin.Model.DataModelLokasi;
import com.example.checkvaksin.Model.ResponseModelLokasi;
import com.example.checkvaksin.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterLokasi extends RecyclerView.Adapter<AdapterLokasi.HolderDataLokasi>{
    private Context ctx;
    private int idDetailLokasi;
    private List<DataModelLokasi> listdatalokasi;
    private List<DataModelLokasi> getdatalokasi;
    String lati = MenuUtamaActivity.mMyAppsBundle.getString("xlatitude");
    String longi = MenuUtamaActivity.mMyAppsBundle.getString("xlongitude");
    private int Rotasi = 6371;


    public double toradians(double deg){
        return deg * (Math.PI/180);
    }

    public AdapterLokasi(Context ctx, List<DataModelLokasi> listdatalokasi) {
        this.ctx = ctx;
        this.listdatalokasi = listdatalokasi;
    }

    @NonNull
    @NotNull
    @Override
    public HolderDataLokasi onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlistvaksin, parent, false);
        HolderDataLokasi holderlokasi= new HolderDataLokasi(layout);
        return holderlokasi;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterLokasi.HolderDataLokasi holder, int position) {
        DataModelLokasi dml = listdatalokasi.get(position);
        Double latitude = dml.getLatitude();
        Double longitude = dml.getLongitude();
        Double latitudeawal = Double.parseDouble(lati);
        Double longitudeawal = Double.parseDouble(longi);

        double dlat = toradians(latitude - latitudeawal);
        double dlon = toradians(longitude - longitudeawal);
        latitudeawal = toradians(latitudeawal);
        latitude = toradians(latitude);

        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.sin(dlon/2) * Math.sin(dlon/2) * Math.cos(latitudeawal) * Math.cos(latitude);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double jaraktotal =  Rotasi * c;
        short hasil = jaraktotal.shortValue();
        double hasilnyaa = Math.round(jaraktotal*100.0)/100.0;

        holder.tv_id_lokasi.setText(String.valueOf(dml.getId()));
        holder.tv_nama_lokasi.setText(dml.getNama_tempat());
        holder.tv_latitude.setText(String.valueOf(latitude));
        holder.tv_longitude.setText(String.valueOf(longitude));
        holder.tv_alamat.setText(dml.getAlamat());
        holder.tv_jarak.setText(String.valueOf(hasilnyaa));
    }

    @Override
    public int getItemCount() {
        return listdatalokasi.size();
    }

    public class HolderDataLokasi extends RecyclerView.ViewHolder{
        TextView tv_id_lokasi, tv_nama_lokasi, tv_jarak, tv_latitude, tv_longitude ,tv_alamat;

        public HolderDataLokasi(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_id_lokasi = itemView.findViewById(R.id.tv_id_lokasi);
            tv_nama_lokasi = itemView.findViewById(R.id.tv_nama_lokasi);
            tv_jarak = itemView.findViewById(R.id.tv_jarak);
            tv_latitude = itemView.findViewById(R.id.tv_latitude);
            tv_longitude = itemView.findViewById(R.id.tv_longitude);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idDetailLokasi = Integer.parseInt(tv_id_lokasi.getText().toString());
                    AdapterDetailLokasi.mMyAppsBundle.putString("xid_lokasi", String.valueOf(idDetailLokasi));
                    //Intent intent = new Intent(ctx, LokasiVaksin.class);
                    //ctx.startActivity(intent);
                    getDataDetail();
                }
            });
        }
    }

    private void getDataDetail(){
        APIRequestData ardDataLokasi = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelLokasi> ambildataLokasi = ardDataLokasi.ardGetLokasi(idDetailLokasi);

        ambildataLokasi.enqueue(new Callback<ResponseModelLokasi>() {
            @Override
            public void onResponse(Call<ResponseModelLokasi> call, Response<ResponseModelLokasi> response) {
                getdatalokasi = response.body().getData();

                int varId =  getdatalokasi.get(0).getId();
                String varnamatempat =  getdatalokasi.get(0).getNama_tempat();
                double varlatitude =  getdatalokasi.get(0).getLatitude();
                double varlongitude =  getdatalokasi.get(0).getLongitude();
                String varalamat =  getdatalokasi.get(0).getAlamat();
                String vartelepon =  getdatalokasi.get(0).getTelepon();

                Intent kirim = new Intent(ctx, LokasiVaksin.class);
                kirim.putExtra("xid", varId);
                kirim.putExtra("xnamatempat", varnamatempat);
                kirim.putExtra("xlatitude", varlatitude);
                kirim.putExtra("xlongitude", varlongitude);
                kirim.putExtra("xalamat", varalamat);
                kirim.putExtra("xtelepon", vartelepon);

                ctx.startActivity(kirim);
            }

            @Override
            public void onFailure(Call<ResponseModelLokasi> call, Throwable t) {

            }
        });
    }
}
