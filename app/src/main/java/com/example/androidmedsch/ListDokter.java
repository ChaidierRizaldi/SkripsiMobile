package com.example.androidmedsch;

public class ListDokter {
    private int image_pengajar;
    private String nama_pengajar;
    private String gelar_pengajar;

    public ListDokter(int img_pengajar, String nama, String gelar) {
        image_pengajar = img_pengajar;
        nama_pengajar = nama;
        gelar_pengajar = gelar;
    }
    public int getmImagePengajar() {
        return image_pengajar;
    }

    public String getNama() {
        return nama_pengajar;
    }

    public String getGelar() {
        return gelar_pengajar;
    }
}
