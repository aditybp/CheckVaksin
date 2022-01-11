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
import com.example.checkvaksin.Activity.Detail_jenis_vaksin;
import com.example.checkvaksin.Model.DataModel;
import com.example.checkvaksin.Model.ResponseModel;
import com.example.checkvaksin.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listDetail;
    private int idDatabase;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterData.HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tv_id.setText(String.valueOf(dm.getId()));
        holder.tv_nama_vaksin.setText(dm.getNama_vaksin());
        holder.tv_keterangan.setText(dm.getKeterangan());
        holder.tv_dosis.setText(dm.getDosis());
        holder.tv_jumlah_suntikan.setText(dm.getJumlah_suntikan());
        holder.tv_jarak_suntikan.setText(dm.getJarak_suntikan());
        holder.tv_batas_umur.setText(dm.getBatas_umur());
        holder.tv_jumlah_vaksin.setText(dm.getJumlah_vaksin());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tv_id, im_vaksin, tv_nama_vaksin, tv_keterangan, tv_dosis, tv_jumlah_suntikan, tv_jarak_suntikan, tv_batas_umur, tv_jumlah_vaksin;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_nama_vaksin = itemView.findViewById(R.id.tv_nama_vaksin);
            tv_keterangan = itemView.findViewById(R.id.tv_keterangan);
            tv_dosis = itemView.findViewById(R.id.tv_dosis);
            tv_jumlah_suntikan = itemView.findViewById(R.id.tv_jumlah_suntikan);
            tv_jarak_suntikan = itemView.findViewById(R.id.tv_jarak_suntikan);
            tv_batas_umur = itemView.findViewById(R.id.tv_batas_umur);
            tv_jumlah_vaksin = itemView.findViewById(R.id.tv_jumlah_vaksin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idDatabase = Integer.parseInt(tv_id.getText().toString());
                    getData();
                }
            });
        }
    }

    private void getData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ambildata = ardData.ardGetData(idDatabase);

        ambildata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                listDetail = response.body().getData();

                int varId =  listDetail.get(0).getId();
                String varnama =  listDetail.get(0).getNama_vaksin();
                String varketerangan =  listDetail.get(0).getKeterangan();
                String vardosis =  listDetail.get(0).getDosis();
                String varjumlahsuntikan =  listDetail.get(0).getJumlah_suntikan();
                String varjaraksuntikan =  listDetail.get(0).getJarak_suntikan();
                String varbatasumur =  listDetail.get(0).getBatas_umur();
                String varjumlahvaksin =  listDetail.get(0).getJumlah_vaksin();
                String vargambar =  listDetail.get(0).getGambar();

                Intent kirim = new Intent(ctx, Detail_jenis_vaksin.class);
                kirim.putExtra("xid", varId);
                kirim.putExtra("xnama", varnama);
                kirim.putExtra("xketerangan", varketerangan);
                kirim.putExtra("xdosis", vardosis);
                kirim.putExtra("xjumlahsuntikan", varjumlahsuntikan);
                kirim.putExtra("xjaraksuntikan", varjaraksuntikan);
                kirim.putExtra("xbatasumur", varbatasumur);
                kirim.putExtra("xjumlahvaksin", varjumlahvaksin);
                kirim.putExtra("xgambar", vargambar);

                ctx.startActivity(kirim);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }


}
