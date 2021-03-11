package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.airbnb.lottie.L;

import java.util.ArrayList;

public class DaftarDokter extends AppCompatActivity implements ListDokterAdapter.ListItemClickListener {
    private RecyclerView rv_dokter;
    private RecyclerView.Adapter adapter;
    private AlertDialog dialog_dokter; //untuk dialog info dokter
    ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_daftar_dokter);

        rv_dokter = findViewById(R.id.rv_daftar_dokter);
        rv_dokter.setHasFixedSize(true);
        rv_dokter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ArrayList<ListDokter> listDokter = new ArrayList<>();
        listDokter.add(new ListDokter(R.drawable.doctor1,"Dr. Bali","Dokter THT"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Mar","Dokter Jantung"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Dharma","Dokter Kehamilan"));
        listDokter.add(new ListDokter(R.drawable.doctor1,"Dr. Bali","Dokter THT"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Mar","Dokter Jantung"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Dharma","Dokter Kehamilan"));
        listDokter.add(new ListDokter(R.drawable.doctor1,"Dr. Bali","Dokter THT"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Mar","Dokter Jantung"));
        listDokter.add(new ListDokter(R.drawable.doctor2,"Dr. Dharma","Dokter Kehamilan"));

        adapter = new ListDokterAdapter(listDokter,this);
        rv_dokter.setAdapter(adapter);

        button = findViewById(R.id.btn_back_daftar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarDokter.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClickListener(int clickedItem) {

    }
}
