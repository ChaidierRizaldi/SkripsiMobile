package com.example.androidmedsch.model.post.register;

import com.google.gson.annotations.SerializedName;

public class RequestRegister{

	@SerializedName("password")
	private String password;

	@SerializedName("nim")
	private String nim;

	@SerializedName("namaLengkap")
	private String namaLengkap;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public void setNim(String nim){
		this.nim = nim;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public void setEmail(String email){
		this.email = email;
	}
}