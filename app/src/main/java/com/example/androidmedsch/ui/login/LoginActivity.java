package com.example.androidmedsch.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.androidmedsch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager view_pager;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        tab_layout = findViewById(R.id.tab_layout);
        TabItem tabLogin = findViewById(R.id.login);
        TabItem tabSignUp = findViewById(R.id.signup);

        view_pager = findViewById(R.id.view_pager);

        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), tab_layout.getTabCount());

        view_pager.setAdapter(adapter);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
