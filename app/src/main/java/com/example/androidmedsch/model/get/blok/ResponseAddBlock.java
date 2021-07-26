package com.example.androidmedsch.model.get.blok;

import com.google.gson.annotations.SerializedName;

public class ResponseAddBlock{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}