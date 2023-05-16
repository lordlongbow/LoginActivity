package com.example.loginactivity.ui.register;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginactivity.databinding.RegistroActivityBinding;
import com.example.loginactivity.modelos.Usuario;

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
        vm.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombreUsuario.setText(usuario.getNombre());
                binding.etApllidoUsuario.setText(usuario.getApellido());
                binding.etEmail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
                binding.etDni.setText((int) usuario.getDni());
            }
        });

        //cuando el usuario toque en registro valida los datos y los guarda
        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             vm.registro();
            }
        });

    }

}
