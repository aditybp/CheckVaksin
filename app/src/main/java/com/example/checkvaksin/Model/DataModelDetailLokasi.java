package com.example.checkvaksin.Model;

public class DataModelDetailLokasi {
    private int id_detail_lokasi, id_lokasi, id;
    private String nama_vaksin, stock_vaksin, dosis_1, dosis_2;

    public int getId_detail_lokasi() {
        return id_detail_lokasi;
    }

    public void setId_detail_lokasi(int id_detail_lokasi) {
        this.id_detail_lokasi = id_detail_lokasi;
    }

    public int getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(int id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_vaksin() {
        return nama_vaksin;
    }

    public void setNama_vaksin(String nama_vaksin) {
        this.nama_vaksin = nama_vaksin;
    }

    public String getStock_vaksin() {
        return stock_vaksin;
    }

    public void setStock_vaksin(String stock_vaksin) {
        this.stock_vaksin = stock_vaksin;
    }

    public String getDosis_1() {
        return dosis_1;
    }

    public void setDosis_1(String dosis_1) {
        this.dosis_1 = dosis_1;
    }

    public String getDosis_2() {
        return dosis_2;
    }

    public void setDosis_2(String dosis_2) {
        this.dosis_2 = dosis_2;
    }
}
