package com.example.androidmedsch.model.get.login.get;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("nim")
	private String nim;

	@SerializedName("idKelompok")
	private int idKelompok;

	@SerializedName("angkatan")
	private String angkatan;

	@SerializedName("idMhs")
	private int idMhs;

	@SerializedName("namaLengkap")
	private String namaLengkap;

	@SerializedName("email")
	private String email;

	public String getNim(){
		return nim;
	}

	public int getIdKelompok(){
		return idKelompok;
	}

	public String getAngkatan(){
		return angkatan;
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
}