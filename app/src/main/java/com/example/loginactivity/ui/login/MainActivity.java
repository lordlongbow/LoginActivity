package com.example.loginactivity.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginactivity.R;
import com.example.loginactivity.databinding.ActivityMainBinding;
import com.example.loginactivity.request.ApiClient;
import com.example.loginactivity.ui.register.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        //Cuando el susuario toque el boton login valida los campos y si todo esta bien lo redirige al registroActivity con sus datos
    binding.btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vm.login( binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
        }
    });

    //Si el usuario toca en el boton registro lo redirige al RegistroAtivity pero con los campos vacios
    binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         vm.vacio();
        }
    });
    }


}