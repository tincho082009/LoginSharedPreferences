package com.example.login.ui.login;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.login.model.Usuario;
import com.example.login.request.ApiClient;
import com.example.login.ui.registro.RegistroActivity;

public class MainViewModel extends AndroidViewModel {
    private ApiClient ac;
    private MutableLiveData<Boolean> activadorIntent;
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Boolean> getActivadorIntent(){
        if(activadorIntent == null){
            activadorIntent = new MutableLiveData<>();
        }
        return activadorIntent;
    }

    public void validar(String mail, String contra){
        Usuario userXD = ac.leer(getApplication());
        if(userXD.getEmail().equals("-1")){
            Toast.makeText(getApplication(), "Ponete las pilas flaco! Registrate", Toast.LENGTH_LONG).show();
        }else{
            Usuario userNuevito = ac.login(getApplication(), mail, contra);
            if(userNuevito != null){
                activadorIntent.setValue(true);
            }else{
                Toast.makeText(getApplication(), "Datos incorrectos", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void registrar(){
        Usuario user = new Usuario();
        ac.guardar(getApplication(), user);
        activadorIntent.setValue(false);
    }


}
