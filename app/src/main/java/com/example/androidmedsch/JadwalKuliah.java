package com.example.androidmedsch;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmedsch.databinding.ActivityJadwalKuliahBinding;
import com.example.androidmedsch.model.get.blok.BlokByIdMahasiswa;
import com.example.androidmedsch.model.get.jadwal.bytanggal.JadwalByKelompok;
import com.example.androidmedsch.utils.Retrofit;
import com.example.androidmedsch.utils.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalKuliah extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    private ActivityJadwalKuliahBinding binding_jadwal;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_jadwal = ActivityJadwalKuliahBinding.inflate(getLayoutInflater());
        View view = binding_jadwal.getRoot();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        ArrayList<JadwalByKelompok> list_jadwal = new ArrayList<>();

        preferences = new SharedPreferences(this);
        binding_jadwal.rvJadwalKuliah.setLayoutManager(new LinearLayoutManager(JadwalKuliah.this, LinearLayoutManager.HORIZONTAL, false));

        getDataBlok();
    }

    private void getDataBlok() {
        ArrayAdapter<String> blokAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        ArrayList<String> data_blok = new ArrayList<>();

        blokAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding_jadwal.spinnerBlok.setAdapter(blokAdapter);

        HashMap<String, String> detail_user = preferences.getDetailUser();

        int id_kelompok = Integer.parseInt(detail_user.get(SharedPreferences.KELOMPOK));
        int id_angkatan = Integer.parseInt(detail_user.get(SharedPreferences.ANGKATAN));

        Call<List<BlokByIdMahasiswa>> blok_mahasiswa = Retrofit.endpoints().getAllBlok(1);
        blok_mahasiswa.enqueue(new Callback<List<BlokByIdMahasiswa>>(){
            @Override
            public void onResponse(Call<List<BlokByIdMahasiswa>> call, Response<List<BlokByIdMahasiswa>> response) {
                if (response.isSuccessful()){
                    Log.d("Response", String.valueOf(response.code()));
                    List<BlokByIdMahasiswa> data_response = response.body();
                    data_blok.clear();

                    data_response.forEach(blokByIdMahasiswa -> {
                        data_blok.add(blokByIdMahasiswa.getNamaBlok());
                    });

                    blokAdapter.addAll(data_blok);
                    blokAdapter.notifyDataSetChanged();

                    Log.d("Data Blok", data_response.toString());

                    binding_jadwal.spinnerBlok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            binding_jadwal.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                Integer id_blok = position+1;
                                @Override
                                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                        Call<List<JadwalByKelompok>> jadwalKuliah = Retrofit.endpoints().getJadwalByKelompok(id_kelompok,id_blok, dayOfMonth, id_angkatan);
                                        jadwalKuliah.enqueue(new Callback<List<JadwalByKelompok>>() {
                                            @Override
                                            public void onResponse(Call<List<JadwalByKelompok>> call, Response<List<JadwalByKelompok>> response) {
                                                if (response.isSuccessful()){
                                                    List<JadwalByKelompok> list_kelompok = response.body();
                                                    adapter = new ListJadwalAdapter(list_kelompok);
                                                    binding_jadwal.rvJadwalKuliah.setAdapter(adapter);
                                                    if (list_kelompok.isEmpty()){
                                                        Toast.makeText(JadwalKuliah.this, "Tidak ada Jadwal", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<List<JadwalByKelompok>> call, Throwable t) {

                                            }
                                        });
                                }
                            });
//                            data_response.forEach(blokByIdMahasiswa -> {
//                                Call<List<JadwalByKelompok>> jadwalKuliah = Retrofit.endpoints().getJadwalByKelompok(id_kelompok,blokByIdMahasiswa.getIdBlok(),position, id_angkatan);
//                                jadwalKuliah.enqueue(new Callback<List<JadwalByKelompok>>() {
//                                    @Override
//                                    public void onResponse(Call<List<JadwalByKelompok>> call, Response<List<JadwalByKelompok>> response) {
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<List<JadwalByKelompok>> call, Throwable t) {
//
//                                    }
//                                });
//                            });
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

        binding_jadwal.btnBackJadwalKuliah.setOnClickListener(v->{
            onBackPressed();
        });

    }
}