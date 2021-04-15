package com.example.androidmedsch.model.get.register.get;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("namaLengkap")
	private String namaLengkap;

	@SerializedName("email")
	private String email;

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public String getEmail(){
		return email;
	}
}