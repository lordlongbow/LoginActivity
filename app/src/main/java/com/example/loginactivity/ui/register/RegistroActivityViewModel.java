package com.example.loginactivity.ui.register;

import static com.example.loginactivity.request.ApiClient.guardar;
import static com.example.loginactivity.request.ApiClient.leer;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginactivity.modelos.Usuario;
import com.example.loginactivity.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Usuario> usuarioMutable;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.contexto = contexto;
    }

    public LiveData<Usuario> getUsuarioMutable()
    {
        if(usuarioMutable == null){
            this.usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    //Registro trae los datos de los edittext y los valida
    public void registro (){
        Usuario usuario = leer(contexto);
        if(validar(usuario)){
            guardar(contexto, usuario);
            usuarioMutable.setValue(usuario);
            Toast.makeText(contexto, "Usuario registrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(contexto, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto.startActivity(intent);
        }
    }

    //valida los datos que no esten vacios, si estan vacios envia un toast con un mensaje, sino lee los datos y los guarda
    public Boolean validar(Usuario usuario){
        if(usuario.getMail().length() == 0 || usuario.getPassword().length() ==0 || usuario.getNombre().length() == 0 || usuario.getApellido().length() == 0 || usuario.getDni() == 0){
            mensaje();
            return false;
        }
        return true;
    }

    public void mensaje(){
        Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
    }


}
