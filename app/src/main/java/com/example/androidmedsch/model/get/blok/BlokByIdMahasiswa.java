package com.example.androidmedsch.model.get.blok;

import com.google.gson.annotations.SerializedName;

public class BlokByIdMahasiswa{

	@SerializedName("id_blok")
	private int idBlok;

	@SerializedName("nama_blok")
	private String namaBlok;

	public int getIdBlok(){
		return idBlok;
	}

	public String getNamaBlok(){
		return namaBlok;
	}
}