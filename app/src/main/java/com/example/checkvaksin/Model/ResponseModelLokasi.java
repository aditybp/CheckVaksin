package com.example.checkvaksin.Model;

import java.util.List;

public class ResponseModelLokasi {
    private int kode;
    private String pesan;
    private List<DataModelLokasi> data;

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

    public List<DataModelLokasi> getData() {
        return data;
    }

    public void setData(List<DataModelLokasi> data) {
        this.data = data;
    }
}
