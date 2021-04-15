package com.example.androidmedsch.model.get.jadwal.updatestatus.post;

import com.google.gson.annotations.SerializedName;

public class RequestUpdateStatus{

	@SerializedName("idJadwal")
	private int idJadwal;

	@SerializedName("status")
	private int status;

	public int getIdJadwal(){
		return idJadwal;
	}

	public int getStatus(){
		return status;
	}
}