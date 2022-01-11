package com.example.checkvaksin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkvaksin.Activity.LokasiVaksin;
import com.example.checkvaksin.Model.DataModelDetailLokasi;
import com.example.checkvaksin.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterDetailLokasi extends RecyclerView.Adapter<AdapterDetailLokasi.HolderDetaiLokasi>{
    private Context ctx;
    private List<DataModelDetailLokasi> listdetaillokasi;

    public static Bundle mMyAppsBundle = new Bundle();

    public AdapterDetailLokasi(Context ctx, List<DataModelDetailLokasi> listdetaillokasi) {
        this.ctx = ctx;
        this.listdetaillokasi = listdetaillokasi;
    }

    @NonNull
    @NotNull
    @Override
    public HolderDetaiLokasi onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlistdetail, parent, false);
        HolderDetaiLokasi holder = new HolderDetaiLokasi(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterDetailLokasi.HolderDetaiLokasi holder, int position) {
        DataModelDetailLokasi dmdetail = listdetaillokasi.get(position);

        holder.tv_id_detail_lokasi.setText(String.valueOf(dmdetail.getId_detail_lokasi()));
        holder.tv_id_lokasi.setText(String.valueOf(dmdetail.getId_lokasi()));
        holder.tv_nama_vaksin_detail.setText(dmdetail.getNama_vaksin());
        holder.tv_stock_vaksin_detail.setText(dmdetail.getStock_vaksin());
        holder.tv_dosis1.setText(dmdetail.getDosis_1());
        holder.tv_dosis2.setText(dmdetail.getDosis_2());
    }

    @Override
    public int getItemCount() {
        return listdetaillokasi.size();
    }

    public class HolderDetaiLokasi extends RecyclerView.ViewHolder{
        TextView tv_id_lokasi, tv_nama_vaksin_detail, tv_stock_vaksin_detail, tv_dosis1, tv_dosis2, tv_id_detail_lokasi;


        public HolderDetaiLokasi(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_id_lokasi = itemView.findViewById(R.id.tv_id_lokasi);
            tv_nama_vaksin_detail = itemView.findViewById(R.id.tv_nama_vaksin_detail);
            tv_stock_vaksin_detail = itemView.findViewById(R.id.tv_stock_vaksin_detail);
            tv_dosis1 = itemView.findViewById(R.id.tv_dosis1);
            tv_dosis2 = itemView.findViewById(R.id.tv_dosis2);
            tv_id_detail_lokasi = itemView.findViewById(R.id.tv_id_detail_lokasi);


        }
    }

}
