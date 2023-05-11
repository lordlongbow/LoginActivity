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

    //La funcion llama al metodo validar, luego llama a la funcion login del ApiClient para crear el usuario, luego guarda los datos para mostrarlos en el registroActivity
    //despues a traves de un intent redirige al registroActivity usando una bandera porque estamos en el viewModel
    public void login(Context contexto, String email, String password){
       ApiClient ap = new ApiClient();
       validar(contexto, email, password);
       Usuario usuario = ap.login(contexto, email, password);
       guardar(contexto, usuario);
       Intent intent = new Intent(contexto, RegistroActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       contexto.startActivity(intent);
    }


    //La funcion valida los datos si estos estan vacios lo redirige al RegistroActivity con los campos vacios para que se registre
    // y agrega un toast recordandole que los campos no deben estar vacios, si usuario o contraseña son incorrectos muestra un toast con l mensaje
    // si esta todo bien valida la entrada.
    public void validar(Context contexto, String email, String password){
        if(email.isEmpty() && password.isEmpty()){
            vacio(contexto);
            Toast.makeText(contexto, "No puede haber campos vacios", Toast.LENGTH_SHORT).show();
        }
        else if(!email.equals(email) && !password.equals(password)){
            Toast.makeText(contexto, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }else{
            leer(contexto);
            Toast.makeText(contexto, "Bienvenido", Toast.LENGTH_SHORT).show();
        }
    }

    //Si los datos estan vacios redirige al RegistroActivity para que se registre
    public void vacio(Context contexto){
        Intent intent = new Intent(contexto, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }
}
