package com.example.androidmedsch.ui.menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.androidmedsch.DaftarDokter;
import com.example.androidmedsch.JadwalKuliah;
import com.example.androidmedsch.R;
import com.example.androidmedsch.databinding.FragmentDashboardBinding;
import com.example.androidmedsch.ui.login.LoginActivity;
import com.example.androidmedsch.utils.SharedPreferences;


public class FragmentDashboard extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnJadwalKuliah.setOnClickListener(v -> {
            Intent intent = new Intent(FragmentDashboard.this.requireContext(), JadwalKuliah.class);
            startActivity(intent);
        });

        binding.btnListDokter.setOnClickListener(v->{
            Intent intent = new Intent(FragmentDashboard.this.requireContext(), DaftarDokter.class);
            startActivity(intent);
        });

        binding.btnExitApps.setOnClickListener(v->{
            FragmentDashboard.this.getActivity().finish();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}