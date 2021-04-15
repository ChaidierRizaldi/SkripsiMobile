package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
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
    ImageButton button;
    List<AllDokter> list_dokter;
    Dialog dialog_info_dokter;
    private ActivityDaftarDokterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDaftarDokterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        list_dokter = new ArrayList<>();

        dialog_info_dokter = new Dialog(DaftarDokter.this);
        dialog_info_dokter.setContentView(R.layout.dialog_info_dokter);
        dialog_info_dokter.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_info_dokter.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog_info_dokter.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        binding.rvDaftarDokter.setLayoutManager(new LinearLayoutManager(DaftarDokter.this, LinearLayoutManager.VERTICAL, false));

        Call<List<AllDokter>> retro_list_dokter = Retrofit.endpoints().getAllDokter();

        retro_list_dokter.enqueue(new Callback<List<AllDokter>>() {
            @Override
            public void onResponse(Call<List<AllDokter>> call, Response<List<AllDokter>> response) {
                if (response.isSuccessful()){
                    List<AllDokter> data_dokter = response.body();
                    adapter = new ListDokterAdapter(data_dokter, DaftarDokter.this);
                    binding.rvDaftarDokter.setAdapter(adapter);
                        Log.d("Data Dokter", String.valueOf(response.body()));
                }
            }
            @Override
            public void onFailure(Call<List<AllDokter>> call, Throwable t) {
                Toast.makeText(DaftarDokter.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ERROR", t.toString());
            }
        });
//        rv_dokter = findViewById(R.id.rv_daftar_dokter);
//        rv_dokter.setLayoutManager(new LinearLayoutManager(DaftarDokter.this, LinearLayoutManager.VERTICAL, false));
//        rv_dokter.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        rv_dokter.setAdapter(adapter);

//        adapter = new ListDokterAdapter(list_dokter, DaftarDokter.this);
//
//
//        Call<List<ModelAllDokter>> list_dokter = Retrofit.endpoints().getAllDokter();
//
//        list_dokter.enqueue(new Callback<List<ModelAllDokter>>() {
//            @Override
//            public void onResponse(Call<List<ModelAllDokter>> call, Response<List<ModelAllDokter>> response) {
//                if (response.isSuccessful()){
//                    List<ModelAllDokter> data_dokter = response.body();
//                    adapter.setData(data_dokter);
//
//
//                    Toast.makeText(DaftarDokter.this, data_dokter.toString(), Toast.LENGTH_LONG).show();
//                    Log.d("Data", data_dokter.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ModelAllDokter>> call, Throwable t) {
//                Toast.makeText(DaftarDokter.this,t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


        binding.btnBackDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarDokter.this, Dashboard.class);
                startActivity(intent);
            }
        });
//        button = findViewById(R.id.btn_back_daftar);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DaftarDokter.this, Dashboard.class);
//                startActivity(intent);
//            }
//        });
    }

//    @Override
//    public void onItemClick(Integer position) {
//        dialog_info_dokter.show();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        SearchView searchView = findViewById(R.id.cari_pengajar);
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                Log.d("nama", newText);
//                return false;
//            }
//        });
//        return true;
//    }
}
