package com.example.androidmedsch.model.get.blok;

import com.google.gson.annotations.SerializedName;

public class DataBlok {
    @SerializedName("id_blok")
    private int idBlok;

    @SerializedName("nama_blok")
    private String namaBlok;

    @SerializedName("masa_blok")
    private String masaBlok;

    public int getIdBlok(){
        return idBlok;
    }

    public String getNamaBlok(){
        return namaBlok;
    }

    public String getMasaBlok(){ return masaBlok; }
}
