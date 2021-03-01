package com.example.androidmedsch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager view_pager;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabItem tabLogin = findViewById(R.id.login);
        TabItem tabSignUp = findViewById(R.id.signup);

        final ViewPager viewPager = findViewById(R.id.view_pager);

        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        tab_layout = findViewById(R.id.tab_layout);
//        view_pager = findViewById(R.id.view_pager);
//
//        tab_layout.addTab(tab_layout.newTab().setText("Login"));
//        tab_layout.addTab(tab_layout.newTab().setText("Sign Up"));
//
//        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tab_layout.getTabCount());
//        view_pager.setAdapter(adapter);
//        tab_layout.setupWithViewPager(view_pager);
//
//        view_pager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
//        tab_layout.setTranslationY(300);
//
//        tab_layout.setAlpha(v);
    }
}
