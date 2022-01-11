package com.example.checkvaksin.API;

import com.example.checkvaksin.Model.ResponseModel;
import com.example.checkvaksin.Model.ResponseModelDetailLokasi;
import com.example.checkvaksin.Model.ResponseModelLokasi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrive.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @GET("retrivelokasi.php")
    Call<ResponseModelLokasi> ardRetriveLokasi();

    @FormUrlEncoded
    @POST("getlokasidetail.php")
    Call<ResponseModelDetailLokasi> ardGetDetaillokasi(
            @Field("id_lokasi") int id_lokasi
    );

    @FormUrlEncoded
    @POST("getlokasi.php")
    Call<ResponseModelLokasi> ardGetLokasi(
            @Field("id") int id
    );
}
