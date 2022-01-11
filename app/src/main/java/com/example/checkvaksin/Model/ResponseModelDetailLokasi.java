package com.example.checkvaksin.Model;

import java.util.List;

public class ResponseModelDetailLokasi {
    private int kode;
    private String pesan;
    private List<DataModelDetailLokasi> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModelDetailLokasi> getData() {
        return data;
    }

    public void setData(List<DataModelDetailLokasi> data) {
        this.data = data;
    }
}
