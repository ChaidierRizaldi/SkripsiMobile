package com.example.androidmedsch.model.post.login;

import com.google.gson.annotations.SerializedName;

public class AddBlock {

    @SerializedName("id_mhs")
    private String id_mahasiswa;

    @SerializedName("id_blok")
    private int id_blok;

    public void setId_mhs(String id_mhs){
        this.id_mahasiswa = id_mhs;
    }

    public void setId_blok(int id_blok){
        this.id_blok = id_blok;
    }
}
