package com.example.androidmedsch.ui.menu;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidmedsch.DaftarDokter;
import com.example.androidmedsch.JadwalKuliah;
import com.example.androidmedsch.R;
import com.example.androidmedsch.slider.DrawerAdapter;
import com.example.androidmedsch.slider.model.DrawerItem;
import com.example.androidmedsch.slider.model.SimpleItem;
import com.example.androidmedsch.slider.model.SpaceItem;
import com.example.androidmedsch.ui.login.LoginActivity;
import com.example.androidmedsch.utils.SharedPreferences;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class Dashboard extends AppCompatActivity implements DrawerAdapter.onItemSelected {
    private static final int POS_CLOSE = 0;
    private static  final int POS_DASHBOARD = 1;
    private static final int POS_PROFIL = 2;
    private static final int POS_LOGOUT = 3;

    TextView profil, logout;

    private String [] screenTitle;
    private Drawable[] screenIcons;

    SharedPreferences preferences;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar_dashboard);
        setSupportActionBar(toolbar);

        preferences = new SharedPreferences(this);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu_sidebar)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitle = loadScreenTitle();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_CLOSE),
                new SpaceItem(260)
        ));
        adapter.setListener(this);
        adapter.setSelected(POS_DASHBOARD);
//        adapter.setListener(position -> {
//            if (position == POS_LOGOUT){
//                logout.findViewById(R.id.btn_logout);
//
//                logout.setOnClickListener(v->{
//                    preferences.logoutUser();
//                    Intent intent = new Intent(this, LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                });
//            }else if (position == POS_PROFIL){
//                Toast.makeText(this, "PROFIL", Toast.LENGTH_LONG).show();
//            }
//        });
    }
    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position], screenTitle[position])
                .withIcon(color(R.color.black))
                .withText(color(R.color.black))
                .withSelectedText(color(R.color.black))
                .withSelectedIcon(color(R.color.black));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    private Drawable[] loadScreenIcons(){
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcon);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i=0; i< ta.length(); i++){
            int id = ta.getResourceId(i, 0);
            if (id!= 0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String[] loadScreenTitle(){
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }


    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_DASHBOARD){
            FragmentDashboard dashboardFragment = new FragmentDashboard();
            transaction.replace(R.id.container_dashboard, dashboardFragment);
        }
//        else if (position == POS_PROFIL){
//            Toast.makeText(this, "PROFIL", Toast.LENGTH_LONG).show();
//        }else if (position == POS_LOGOUT){
//            Toast.makeText(this, "LOGOUT", Toast.LENGTH_LONG).show();
//        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
