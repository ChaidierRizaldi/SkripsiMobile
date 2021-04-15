package com.example.androidmedsch.model.get.mahasiswa;

import com.google.gson.annotations.SerializedName;

public class DetailMahasiswa{

	@SerializedName("idMhs")
	private int idMhs;

	@SerializedName("namaLengkap")
	private String namaLengkap;

	@SerializedName("email")
	private String email;

	public int getIdMhs(){
		return idMhs;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public String getEmail(){
		return email;
	}
}