package com.example.androidmedsch.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidmedsch.R;
import com.example.androidmedsch.ui.login.LoginActivity;


public class LandingPage extends AppCompatActivity {


    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing_page);


        button = findViewById(R.id.buttonlanding);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
