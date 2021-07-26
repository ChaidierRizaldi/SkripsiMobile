package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmedsch.databinding.ActivityDaftarDokterBinding;
import com.example.androidmedsch.model.get.dokter.AllDokter;
import com.example.androidmedsch.ui.menu.Dashboard;
import com.example.androidmedsch.utils.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarDokter extends AppCompatActivity {
    private ListDokterAdapter adapter;

    TextView tv_nama, tv_jabatan, tv_nidn, tv_nomor_telepon, tv_email, tv_bidang;
    private ActivityDaftarDokterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDaftarDokterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        LayoutInflater factory_dialog = LayoutInflater.from(DaftarDokter.this);
        final View dialog_dokter_view = factory_dialog.inflate(R.layout.dialog_info_dokter, null);
        final AlertDialog dialog_dokter = new AlertDialog.Builder(DaftarDokter.this).create();
        dialog_dokter.setView(dialog_dokter_view);

        dialog_dokter.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_dokter.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog_dokter.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        binding.rvDaftarDokter.setLayoutManager(new LinearLayoutManager(DaftarDokter.this, LinearLayoutManager.VERTICAL, false));

        Call<List<AllDokter>> retro_list_dokter = Retrofit.endpoints().getAllDokter();

        retro_list_dokter.enqueue(new Callback<List<AllDokter>>() {
            @Override
            public void onResponse(Call<List<AllDokter>> call, Response<List<AllDokter>> response) {
                if (response.isSuccessful()){
                    List<AllDokter> data_dokter = response.body();
                    adapter = new ListDokterAdapter(data_dokter);
                    binding.rvDaftarDokter.setAdapter(adapter);

                    adapter.SetOnClickListener((dokter, position) -> {
                        dialog_dokter.create();
                        tv_nama = dialog_dokter_view.findViewById(R.id.dialog_tv_nama_pengajar);
                        tv_email = dialog_dokter_view.findViewById(R.id.dialog_tv_email_pengajar);
                        tv_jabatan = dialog_dokter_view.findViewById(R.id.dialog_tv_jabatan);
                        tv_nidn = dialog_dokter_view.findViewById(R.id.dialog_tv_nidn);
                        tv_nomor_telepon = dialog_dokter_view.findViewById(R.id.dialog_tv_no_hp);
                        tv_bidang =dialog_dokter_view.findViewById(R.id.dialog_tv_bidang_pengajar);


                        tv_nama.setText(dokter.getNama());
                        tv_nomor_telepon.setText(dokter.getNoHp());
                        tv_bidang.setText(dokter.getBidangKepakaran());
                        tv_email.setText(dokter.getEmail());
                        tv_nidn.setText(dokter.getNidn());
                        tv_jabatan.setText(dokter.getJabatan());

                        dialog_dokter.show();

                        Log.d("Data Item", String.valueOf(dokter));
                    });
                    Log.d("Data Dokter", String.valueOf(response.body()));
                }
            }
            @Override
            public void onFailure(Call<List<AllDokter>> call, Throwable t) {
                Toast.makeText(DaftarDokter.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ERROR", t.toString());
            }
        });


        binding.btnBackDaftar.setOnClickListener(v -> {
            Intent intent = new Intent(DaftarDokter.this, Dashboard.class);
            startActivity(intent);
        });
    }
}
