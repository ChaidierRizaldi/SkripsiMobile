package com.example.androidmedsch.model.get.login.get;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("nim")
	private String nim;

	@SerializedName("idMhs")
	private int idMhs;

	@SerializedName("namaLengkap")
	private String namaLengkap;


	@SerializedName("email")
	private String email;

	@SerializedName("kelompok")
	private Integer kelompok;

	@SerializedName("angkatan")
	private String angkatan;


	public String getNim(){
		return nim;
	}

	public int getIdMhs(){
		return idMhs;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public String getEmail(){
		return email;
	}

	public String getAngkatan(){ return angkatan; }

	public Integer getKelompok(){ return kelompok; }
}