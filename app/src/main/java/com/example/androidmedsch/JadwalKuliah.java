package com.example.androidmedsch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class JadwalKuliah extends AppCompatActivity {

    ImageButton button;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jadwal_kuliah);

        recyclerView = findViewById(R.id.rv_jadwal_kuliah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ListJadwal>list_jadwal = new ArrayList<>();
        list_jadwal.add(new ListJadwal("08.00 - 08.50","Farmakologi: Obat-obatan untuk anti Aritmia bapak kau lah", "Dosen Default", "Fakultas Kedokteran UII", "Siap Belajar!"));
        list_jadwal.add(new ListJadwal("08.00 - 08.50","Farmakologi: Obat-obatan untuk anti Aritmia mak kau lah", "Dosen Default", "Fakultas Kedokteran UII", "Siap Belajar!"));
        list_jadwal.add(new ListJadwal("08.00 - 08.50","Farmakologi: Obat-obatan untuk anti Aritmia BPAK KAU", "Dosen Default", "Fakultas Kedokteran UII", "Siap Belajar!"));
        list_jadwal.add(new ListJadwal("08.00 - 08.50","Farmakologi: Obat-obatan untuk anti Aritmia", "Dosen Default", "Fakultas Kedokteran UII", "Siap Belajar!"));
        list_jadwal.add(new ListJadwal("08.00 - 08.50","Farmakologi: Obat-obatan untuk anti Aritmia", "Dosen Default", "Fakultas Kedokteran UII", "Siap Belajar!"));

        adapter = new ListJadwalAdapter(list_jadwal);
        recyclerView.setAdapter(adapter);

        button = findViewById(R.id.btn_back_daftar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JadwalKuliah.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}