package com.example.androidmedsch.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.androidmedsch.R;
import com.example.androidmedsch.SignupTabFragment;
import com.example.androidmedsch.model.get.login.get.ResponseLogin;
import com.example.androidmedsch.model.post.login.RequestLogin;
import com.example.androidmedsch.ui.menu.Dashboard;
import com.example.androidmedsch.utils.Retrofit;
import com.example.androidmedsch.utils.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {

    LoginAdapter loginAdapter;
    ViewPager viewPager;

    EditText email, pass;
    String email_value, pass_value;
    TextView forgetPass;
    Button btn_login;
    ImageView imageView;
    SharedPreferences sp_helper;
    float v = 0;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        sp_helper = new SharedPreferences(this.requireContext());

        email = root.findViewById(R.id.email_login);
        pass = root.findViewById(R.id.password_login);
        forgetPass = root.findViewById(R.id.forget_Pass);
        btn_login = root.findViewById(R.id.buttonlogin);
        imageView = root.findViewById(R.id.imageLogin);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        btn_login.setTranslationX(800);
        imageView.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        btn_login.setAlpha(v);
        imageView.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn_login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        imageView.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        btn_login.setOnClickListener(v -> {
                email_value = email.getText().toString().trim();
                pass_value = pass.getText().toString().trim();

                login(email_value, pass_value);
        });
        return root;
    }

    private void login(String email_value, String pass_value) {
        if (email_value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_value).matches()){
            Toast.makeText(LoginTabFragment.this.requireContext(), "Email Kosong atau Email Tidak Valid", Toast.LENGTH_LONG).show();
        }else if (pass_value.isEmpty()){
            Toast.makeText(LoginTabFragment.this.requireContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        }else {
            RequestLogin requestLogin = new RequestLogin();
            requestLogin.setEmail(email_value);
            requestLogin.setPassword(pass_value);

            Call<ResponseLogin> retrofit_login = Retrofit.endpoints().login(requestLogin);
            retrofit_login.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.isSuccessful()){
                        String id = String.valueOf(response.body().getIdMhs());
                        String nim = response.body().getNim();
                        String email = response.body().getEmail();
                        String nama = response.body().getNamaLengkap();
                        String kelompok = String.valueOf(response.body().getKelompok());
                        String angkatan = response.body().getAngkatan();

                        sp_helper.loginSession(id, nim, email, nama, kelompok, angkatan);
                        Intent intent = new Intent(LoginTabFragment.this.requireContext(), Dashboard.class);
                        startActivity(intent);
                        LoginTabFragment.this.getActivity().finish();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(LoginTabFragment.this.requireContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
