package com.example.androidmedsch.model.get.dokter;

import com.google.gson.annotations.SerializedName;

public class AllDokter{

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_telp")
	private String noHp;

	@SerializedName("id_pengajar")
	private int idPengajar;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("nidn")
	private String nidn;

	@SerializedName("bidang_kepakaran")
	private String bidangKepakaran;

	@SerializedName("email")
	private String email;

	public String getNama(){
		return nama;
	}

	public String getNoHp(){
		return noHp;
	}

	public int getIdPengajar(){
		return idPengajar;
	}

	public String getJabatan(){
		return jabatan;
	}

	public String getNidn(){
		return nidn;
	}

	public String getBidangKepakaran(){
		return bidangKepakaran;
	}

	public String getEmail(){
		return email;
	}
}