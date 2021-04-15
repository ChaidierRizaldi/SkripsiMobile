package com.example.androidmedsch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmedsch.databinding.ActivityJadwalKuliahBinding;
import com.example.androidmedsch.model.get.blok.BlokByIdMahasiswa;
import com.example.androidmedsch.model.get.jadwal.bytanggal.JadwalByTanggal;
import com.example.androidmedsch.ui.menu.Dashboard;
import com.example.androidmedsch.utils.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalKuliah extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    private ActivityJadwalKuliahBinding binding_jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_jadwal = ActivityJadwalKuliahBinding.inflate(getLayoutInflater());
        View view = binding_jadwal.getRoot();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        ArrayList<JadwalByTanggal> list_jadwal = new ArrayList<>();

        adapter = new ListJadwalAdapter(list_jadwal);
        binding_jadwal.rvJadwalKuliah.setLayoutManager(new LinearLayoutManager(JadwalKuliah.this, LinearLayoutManager.HORIZONTAL, false));
        binding_jadwal.rvJadwalKuliah.setAdapter(adapter);

        getDataBlok();
    }

    private void getDataBlok() {
        ArrayAdapter<List<BlokByIdMahasiswa>> blokAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);

        blokAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding_jadwal.spinnerBlok.setAdapter(blokAdapter);

        Call<List<BlokByIdMahasiswa>> blok_mahasiswa = Retrofit.endpoints().getAllBlok(1);
        blok_mahasiswa.enqueue(new Callback<List<BlokByIdMahasiswa>>(){
            @Override
            public void onResponse(Call<List<BlokByIdMahasiswa>> call, Response<List<BlokByIdMahasiswa>> response) {
                if (response.isSuccessful()){
                    Log.d("Response", String.valueOf(response.code()));
                    List<BlokByIdMahasiswa> data_response = response.body();

                    blokAdapter.addAll(data_response);
                    blokAdapter.notifyDataSetChanged();

                    Log.d("Data Blok", data_response.toString());

                    binding_jadwal.spinnerBlok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(JadwalKuliah.this, data_response.get(position).toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<BlokByIdMahasiswa>> call, Throwable t) {
                Toast.makeText(JadwalKuliah.this,"Gagal Mendapatkan Data Blok", Toast.LENGTH_LONG).show();
            }
        });

    }
}