package com.example.loginactivity.ui.register;

import static com.example.loginactivity.request.ApiClient.guardar;
import static com.example.loginactivity.request.ApiClient.leer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginactivity.modelos.Usuario;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<String> emailM, nombreM, apellidoM, passwordM;
    private MutableLiveData<Long> dniM;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getEmailM() {
        if(emailM == null){
            this.emailM = new MutableLiveData();
        }
        return emailM;
    }

    public LiveData<String> getNombreM() {
        if(nombreM == null){
            this.nombreM=new MutableLiveData<>();
        }
        return nombreM;
    }

    public LiveData<String> getApellidoM() {
        if(apellidoM == null){
            this.apellidoM = new MutableLiveData();
        }
        return apellidoM;
    }

    public LiveData<String> getPasswordM() {
        if(passwordM == null){
            this.passwordM = new MutableLiveData();
        }
        return passwordM;
    }

    public LiveData<Long> getDniM() {
        if(dniM == null){
            this.dniM = new MutableLiveData<>();
        }
        return dniM;
    }

    //leervm invoca a la funcion leer del ApiClient
    public void leervm(Context contexto){
        leer(contexto);
    }
    //Registro trae los datos de los edittext y los valida
    public void registro (Context contexto){

        validar(contexto, emailM.getValue(), nombreM.getValue(), apellidoM.getValue(), passwordM.getValue(), dniM.getValue());
       // leervm(contexto);
    }

    //valida los datos que no esten vacios, si estan vacios envia un toast con un mensaje, sino lee los datos y los guarda
    public void validar(Context contexto, String nombre, String apellido, String email, String password, Long dni){

        if(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() || dni == null){
            mensaje();
        }
        Usuario usuario = leer(contexto);
        guardar(contexto, usuario);
    }

    public void mensaje(){
        Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
    }


}
