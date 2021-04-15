package com.example.androidmedsch.model.get.jadwal.bydokter;

import com.google.gson.annotations.SerializedName;

public class JadwalByDokter{

	@SerializedName("ruangKelas")
	private String ruangKelas;

	@SerializedName("mataKuliah")
	private String mataKuliah;

	@SerializedName("namaDokter")
	private String namaDokter;

	@SerializedName("namaBlok")
	private String namaBlok;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("idBlok")
	private int idBlok;

	@SerializedName("idDokter")
	private int idDokter;

	public String getRuangKelas(){
		return ruangKelas;
	}

	public String getMataKuliah(){
		return mataKuliah;
	}

	public String getNamaDokter(){
		return namaDokter;
	}

	public String getNamaBlok(){
		return namaBlok;
	}

	public String getTanggal(){
		return tanggal;
	}

	public int getIdBlok(){
		return idBlok;
	}

	public int getIdDokter(){
		return idDokter;
	}
}