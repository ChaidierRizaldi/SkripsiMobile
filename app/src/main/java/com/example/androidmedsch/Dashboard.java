package com.example.androidmedsch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    ImageButton button_jadwal_kuliah;
    ImageButton button_daftar_dokter;
    ImageButton button_keluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        button_jadwal_kuliah =findViewById(R.id.btn_jadwal_kuliah);
        button_daftar_dokter = findViewById(R.id.btn_daftar_dokter);
        button_keluar = findViewById(R.id.btn_keluar);

        button_jadwal_kuliah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, JadwalKuliah.class);
                startActivity(intent);
            }
        });

        button_daftar_dokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, DaftarDokter.class);
                startActivity(intent);
            }
        });

        button_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }
}
