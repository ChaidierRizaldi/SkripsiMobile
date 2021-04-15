package com.example.androidmedsch.model.post.login;

import com.google.gson.annotations.SerializedName;

public class RequestLogin{

	@SerializedName("password")
	private String password;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public void setEmail(String email){
		this.email = email;
	}
}