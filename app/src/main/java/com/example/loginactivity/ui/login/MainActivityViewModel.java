package com.example.loginactivity.ui.login;

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
import com.example.loginactivity.request.ApiClient;
import com.example.loginactivity.ui.register.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel{

    private Context contexto;
    private MutableLiveData<String> nombre;
    private MutableLiveData<String> password;

    private boolean logueado = false;

    public LiveData<String> getNombre() {
        if(nombre == null){
            MutableLiveData nombre = new MutableLiveData();
        }
        return nombre;
    }

    public LiveData<String> getPassword() {
        if(password == null){
            MutableLiveData password = new MutableLiveData();
        }
        return password;
    }
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public void login(String email, String password){
       ApiClient ap = new ApiClient();
       validar(contexto, email, password);
       Usuario usuario = ap.login(contexto, email, password);
       if(usuario != null){
           guardar(contexto, usuario);
           Intent intent = new Intent(contexto, RegistroActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           logueado= true;
           contexto.startActivity(intent);
       }else{
           logueado=false;
           vacio();
       }
    }
    public void validar(Context contexto, String email, String password){
        if(email.isEmpty() && password.isEmpty()){
            vacio();
            Toast.makeText(contexto, "No puede haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    //Si los datos estan vacios redirige al RegistroActivity para que se registre
    public void vacio(){
        Intent intent = new Intent(contexto, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }
}
