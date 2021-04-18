package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
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
    List<AllDokter> list_dokter;
    TextView tv_nama, tv_jabatan, tv_nidn, tv_nomor_telepon, tv_email;
    private ActivityDaftarDokterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDaftarDokterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        list_dokter = new ArrayList<>();

//        dialog_info_dokter = new View(DaftarDokter.this)
//        dialog_info_dokter.setContentView(R.layout.dialog_info_dokter);
//        dialog_info_dokter.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog_info_dokter.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
//        dialog_info_dokter.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        binding.rvDaftarDokter.setLayoutManager(new LinearLayoutManager(DaftarDokter.this, LinearLayoutManager.VERTICAL, false));

        Call<List<AllDokter>> retro_list_dokter = Retrofit.endpoints().getAllDokter();

        retro_list_dokter.enqueue(new Callback<List<AllDokter>>() {
            @Override
            public void onResponse(Call<List<AllDokter>> call, Response<List<AllDokter>> response) {
                if (response.isSuccessful()){
                    List<AllDokter> data_dokter = response.body();
                    adapter = new ListDokterAdapter(data_dokter, DaftarDokter.this);
                    binding.rvDaftarDokter.setAdapter(adapter);

                    adapter.setOnclickListener(position -> {
                        LayoutInflater factory_dialog = LayoutInflater.from(DaftarDokter.this);
                        final View dialog_dokter_view = factory_dialog.inflate(R.layout.dialog_info_dokter, null);
                        final AlertDialog dialog_dokter = new AlertDialog.Builder(DaftarDokter.this).create();
                        dialog_dokter.setView(dialog_dokter_view);
                        tv_nama = dialog_dokter_view.findViewById(R.id.dialog_tv_nama_pengajar);
                        tv_email = dialog_dokter_view.findViewById(R.id.dialog_tv_email_pengajar);
                        tv_jabatan = dialog_dokter_view.findViewById(R.id.dialog_tv_jabatan);
                        tv_nidn = dialog_dokter_view.findViewById(R.id.dialog_tv_nidn);
                        tv_nomor_telepon = dialog_dokter_view.findViewById(R.id.dialog_tv_no_hp);

                        data_dokter.forEach(allDokter -> {
                            tv_nama.setText(allDokter.getNama());
                            tv_nomor_telepon.setText(allDokter.getNoHp());
                            tv_nidn.setText(allDokter.getNidn());
                            tv_email.setText(allDokter.getEmail());
                            tv_jabatan.setText(allDokter.getJabatan());
                        });
                        dialog_dokter.show();
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


        binding.btnBackDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarDokter.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
