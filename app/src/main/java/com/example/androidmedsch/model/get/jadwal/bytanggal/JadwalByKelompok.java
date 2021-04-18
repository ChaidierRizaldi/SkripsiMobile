package com.example.androidmedsch.model.get.jadwal.bytanggal;

import com.google.gson.annotations.SerializedName;

public class JadwalByKelompok{

	@SerializedName("ruangKelas")
	private String ruangKelas;

	@SerializedName("angkatan")
	private int angkatan;

	@SerializedName("idJadwal")
	private int idJadwal;

	@SerializedName("jam")
	private String jam;

	@SerializedName("mataKuliah")
	private String mataKuliah;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("namaPengajar")
	private String namaPengajar;

	@SerializedName("status")
	private int status;

	public String getRuangKelas(){
		return ruangKelas;
	}

	public int getAngkatan(){
		return angkatan;
	}

	public int getIdJadwal(){
		return idJadwal;
	}

	public String getJam(){
		return jam;
	}

	public String getMataKuliah(){
		return mataKuliah;
	}

	public String getTanggal(){
		return tanggal;
	}

	public String getNamaPengajar(){
		return namaPengajar;
	}

	public int getStatus(){
		return status;
	}
}