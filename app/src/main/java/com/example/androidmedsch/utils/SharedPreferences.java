package com.example.androidmedsch.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SharedPreferences {
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES" ;
    public static final String ID = "ID";
    public static final String NIM = "NIM";
    public static final String EMAIL = "EMAIL";
    public static final String LOGIN = "LOGIN";
    public static final String NAMA_LENGKAP = "NAMA_LENGKAP";
    public static final String ANGKATAN = "ANGKATAN";
    public static final String KELOMPOK = "KELOMPOK";
     public static android.content.SharedPreferences sp_helper;
     public static android.content.SharedPreferences.Editor editor;
     Context context_activity;

    @SuppressLint("CommitPrefEdits")
    public SharedPreferences(Context context){
        context_activity = context;
        sp_helper = context.getSharedPreferences("SP_HELPER", Context.MODE_PRIVATE);
        editor = sp_helper.edit();
    }

    public void loginSession(String id, String nim, String email, String nama, String kelompok, String angkatan){
        editor.putBoolean(LOGIN, true);

        editor.putString(ID, id);
        editor.putString(NIM, nim);
        editor.putString(EMAIL, email);
        editor.putString(NAMA_LENGKAP, nama);
        editor.putString(KELOMPOK, kelompok);
        editor.putString(ANGKATAN, angkatan);

        editor.commit();
    }

    public HashMap<String , String > getDetailUser(){
        HashMap<String, String> data_user = new HashMap<String, String>();

        data_user.put(ID, sp_helper.getString(ID, null));
        data_user.put(NIM, sp_helper.getString(NIM, null));
        data_user.put(EMAIL, sp_helper.getString(EMAIL, null));
        data_user.put(NAMA_LENGKAP, sp_helper.getString(NAMA_LENGKAP, null));
        data_user.put(ANGKATAN, sp_helper.getString(ANGKATAN, null));
        data_user.put(KELOMPOK, sp_helper.getString(KELOMPOK, null));

        return  data_user;
    }

    public boolean checkLogin(){
        return sp_helper.getBoolean(LOGIN, false);
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }
//    public static void onLogin(Context context, Boolean login){
//        editor.putBoolean(LOGIN, login);
//        editor.apply();
//    }
//
//    public static void setNim(Context context, String nim){
//        editor.putString(NIM,nim);
//        editor.apply();
//    }
//    public static void setEmail(Context context, String email){
//        editor.putString(EMAIL, email);
//        editor.apply();
//    }
//    public static void setId(Context context, String id){
//        editor.putString(ID, id);
//        editor.apply();
//    }
}
