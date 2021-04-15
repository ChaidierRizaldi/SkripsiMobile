package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;

import com.example.androidmedsch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding bindingMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingMain = ActivityMainBinding.inflate(getLayoutInflater());
        View view = bindingMain.getRoot();
        setContentView(view);

//        Call<ModelAllMahasiswa> allMahasiswa = Retrofit.endpoints().getAllMahasiswa();
//
//        allMahasiswa.enqueue(new Callback<ModelAllMahasiswa>() {
//            @Override
//            public void onResponse(Call<ModelAllMahasiswa> call, Response<ModelAllMahasiswa> response) {
//                if (response.isSuccessful()){
//
//                    bindingMain.tvNamaMahasiswa.setText(response.body().getResult().get(0).getNamaLengkap());
//                    Log.d("DATA", response.body().toString());
//                    Log.d("response", String.valueOf(response.code()));
//
//                    Toast.makeText(MainActivity.this, response.body().toString(),Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelAllMahasiswa> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_LONG).show();
//                Log.d("error",t.getLocalizedMessage());
//            }
//        });
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        bindingMain.tvResponse.setText(Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress()));
    }
}