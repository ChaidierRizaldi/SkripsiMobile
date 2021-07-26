package com.example.androidmedsch.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.androidmedsch.R;
import com.example.androidmedsch.databinding.ActivityProfilBinding;
import com.example.androidmedsch.utils.SharedPreferences;

import java.util.HashMap;

public class Profil extends AppCompatActivity {

    private ActivityProfilBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = new SharedPreferences(this);
        HashMap<String, String> detail_user = sharedPreferences.getDetailUser();
        String nim = detail_user.get(SharedPreferences.NIM);
        String nama_lengkap = detail_user.get(SharedPreferences.NAMA_LENGKAP);
        String email = detail_user.get(SharedPreferences.EMAIL);


        binding.tvProfilEmail.setText(email);
        binding.tvProfilNama.setText(nama_lengkap);
        binding.tvProfilNim.setText(nim);

        binding.btnBackBeranda.setOnClickListener(v->{
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}