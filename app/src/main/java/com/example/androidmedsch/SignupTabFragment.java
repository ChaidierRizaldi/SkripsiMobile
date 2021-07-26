package com.example.androidmedsch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidmedsch.model.get.blok.ResponseAddBlock;
import com.example.androidmedsch.model.get.login.get.ResponseLogin;
import com.example.androidmedsch.model.get.register.get.ResponseRegister;
import com.example.androidmedsch.model.post.login.AddBlock;
import com.example.androidmedsch.model.post.login.RequestLogin;
import com.example.androidmedsch.model.post.register.RequestRegister;
import com.example.androidmedsch.ui.login.LoginTabFragment;
import com.example.androidmedsch.ui.menu.Dashboard;
import com.example.androidmedsch.utils.Retrofit;
import com.example.androidmedsch.utils.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupTabFragment  extends Fragment {

    Button btn_signUp;
    TextView et_nama, et_email, et_password, et_nim;
    String nama, email, password, nim;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        et_nama = root.findViewById(R.id.nama_lengkap_mahasiswa);
        et_email = root.findViewById(R.id.email_regis_mahasiswa);
        et_password = root.findViewById(R.id.password_mahasiswa);
        et_nim = root.findViewById(R.id.nim_mahasiswa);
        btn_signUp = root.findViewById(R.id.btn_regis);

        sharedPreferences = new SharedPreferences(SignupTabFragment.this.requireContext());

        btn_signUp.setOnClickListener(v -> {
            nama = et_nama.getText().toString();
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            nim = et_nim.getText().toString();

            regis(email, password, nim, nama);
        });
        return root;
    }

    private void regis(String email, String password, String nim, String nama_lengkap) {
        if (email.isEmpty()){
                Toast.makeText(SignupTabFragment.this.requireContext(), "Email Kosong atau Email Tidak Valid", Toast.LENGTH_LONG).show();
            } else if (password.isEmpty()){
                Toast.makeText(SignupTabFragment.this.requireContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }else if (nama.isEmpty()){
                Toast.makeText(SignupTabFragment.this.requireContext(), "Nama Lengkap Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }else if (nim.isEmpty()){
                Toast.makeText(SignupTabFragment.this.requireContext(), "Nim Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }else{
            RequestRegister requestRegister = new RequestRegister();
            requestRegister.setEmail(email);
            requestRegister.setNamaLengkap(nama_lengkap);
            requestRegister.setNim(nim);
            requestRegister.setPassword(password);

            Call<ResponseRegister> retrofit_regist = Retrofit.endpoints().signUp(requestRegister);
            retrofit_regist.enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if (response.isSuccessful()){

                        RequestLogin requestLogin = new RequestLogin();
                        requestLogin.setEmail(response.body().getEmail());
                        requestLogin.setPassword(password);

                        Call<ResponseLogin> retrofit_login = Retrofit.endpoints().login(requestLogin);
                        retrofit_login.enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                if (response.isSuccessful()){

                                    String id = String.valueOf(response.body().getIdMhs());
                                    String nim = response.body().getNim();
                                    String email = response.body().getEmail();
                                    String nama = response.body().getNamaLengkap();
                                    String kelompok = String.valueOf(response.body().getIdKelompok());
                                    String angkatan = response.body().getAngkatan();

                                    sharedPreferences.loginSession(id, nim, email, nama, kelompok, angkatan);

                                    AddBlock body_request = new AddBlock();
                                    body_request.setId_blok(1);
                                    body_request.setId_mhs(id);

                                    Call<ResponseAddBlock> add_blok = Retrofit.endpoints().addBlock(body_request);
                                    add_blok.enqueue(new Callback<ResponseAddBlock>() {
                                        @Override
                                        public void onResponse(Call<ResponseAddBlock> call, Response<ResponseAddBlock> response_blok) {
                                            if (response_blok.isSuccessful()){
                                                Intent intent = new Intent(SignupTabFragment.this.requireContext(), Dashboard.class);
                                                startActivity(intent);
                                                SignupTabFragment.this.getActivity().finish();
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                Toast.makeText(SignupTabFragment.this.requireContext(), "Login Sukses", Toast.LENGTH_LONG).show();
                                            }else {
                                                Log.d("Error Add Block", String.valueOf(response_blok.errorBody()));
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseAddBlock> call, Throwable t) {
                                            Toast.makeText(SignupTabFragment.this.requireContext(), "Gagal Menambahkan Blok Baru Pada Mahasiswa", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                Toast.makeText(SignupTabFragment.this.requireContext(), "GAGAL LOGIN", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                    if (!response.isSuccessful()){
                        Toast.makeText(SignupTabFragment.this.requireContext(), response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Toast.makeText(SignupTabFragment.this.requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
