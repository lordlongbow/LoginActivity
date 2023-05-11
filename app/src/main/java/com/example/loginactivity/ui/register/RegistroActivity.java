package com.example.loginactivity.ui.register;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginactivity.databinding.RegistroActivityBinding;

public class RegistroActivity extends AppCompatActivity {
private RegistroActivityViewModel vm;
private RegistroActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegistroActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);


        //Observers de los editTexts;
        vm.getApellidoM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etApllidoUsuario.setText(s);
            }
        });
        vm.getDniM().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                binding.etDni.setText(String.valueOf(aLong));
            }
        });
        vm.getEmailM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etEmail.setText(s);
            }
        });
        vm.getNombreM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String nombre) {
                binding.etNombreUsuario.setText(nombre);
            }
        });
        vm.getPasswordM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String password) {
                binding.etPassword.setText(password);
            }
        });

        vm.leervm(RegistroActivity.this);

        //cuando el usuario toque en registro valida los datos y los guarda
        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             vm.registro(RegistroActivity.this);
            }
        });

    }

}
