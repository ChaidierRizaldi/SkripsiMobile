package com.example.androidmedsch.utils;

import com.example.androidmedsch.model.get.blok.BlokByIdMahasiswa;
import com.example.androidmedsch.model.get.blok.DataBlok;
import com.example.androidmedsch.model.get.blok.ResponseAddBlock;
import com.example.androidmedsch.model.get.dokter.AllDokter;
import com.example.androidmedsch.model.get.jadwal.bydokter.JadwalByDokter;
import com.example.androidmedsch.model.get.jadwal.bytanggal.JadwalByKelompok;
import com.example.androidmedsch.model.get.jadwal.updatestatus.get.ResponseUpdateStatus;
import com.example.androidmedsch.model.get.jadwal.updatestatus.post.RequestUpdateStatus;
import com.example.androidmedsch.model.get.login.get.ResponseLogin;
import com.example.androidmedsch.model.get.mahasiswa.DetailMahasiswa;
import com.example.androidmedsch.model.get.register.get.ResponseRegister;
import com.example.androidmedsch.model.post.login.AddBlock;
import com.example.androidmedsch.model.post.login.RequestLogin;
import com.example.androidmedsch.model.post.register.RequestRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Endpoints {
    @GET("mahasiswa/{idMhs}")
    Call<DetailMahasiswa> getDetailMahasiswa(@Path("idMhs")int id);

    @GET("dokter")
    Call<List<AllDokter>> getAllDokter();

    //GET BLOK MAHASISWA
    @GET("blok/getByMhsId")
    Call<List<BlokByIdMahasiswa>> getAllBlok(@Query("idMhs")int id);

    @GET("blok/getById/{id_blok}")
    Call<DataBlok> getDataBlok(@Path("id_blok") int id_blok);

    //GET JADWAL BY TANGGAL DAN BLOK
    @GET("jadwal/getByKelompok")
    Call<List<JadwalByKelompok>> getJadwalByKelompok(@Query("idKelompok")int id, @Query("idBlok")int id_blok, @Query("tanggal")String tanggal, @Query("angkatan")int id_angkatan);

    //GET JADWAL BY DOKTER
    @GET("jadwal/getByDokterId")
    Call<List<JadwalByDokter>> getJadwalByDokter(@Query("idDokter")int id);

    @GET("jadwal/{idMhs}")
    Call<List<JadwalByKelompok>> getAllJadwalMahasiswa(@Path("idMhs")int idMhs);

    @POST("jadwal/updateStatus")
    Call<ResponseUpdateStatus> getStatus(@Body RequestUpdateStatus body_status);

    //POST
    //LOGIN
    @POST("mahasiswa/login")
    Call<ResponseLogin> login(@Body RequestLogin body_login);

    //REGISTER
    @POST("mahasiswa/signup")
    Call<ResponseRegister> signUp(@Body RequestRegister body_register);

    //ADDBLOCK
    @POST("blok/addBlokToMhs")
    Call<ResponseAddBlock> addBlock(@Body AddBlock body_blok);


}
