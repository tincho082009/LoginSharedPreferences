package com.example.login.ui.registro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.login.model.Usuario;
import com.example.login.request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private ApiClient ac;
    private MutableLiveData<Usuario> user;
    private MutableLiveData<String> noUser;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getUser(){
        if(user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }

    public LiveData<String> getNoUser(){
        if(noUser == null){
            noUser = new MutableLiveData<>();
        }
        return noUser;
    }

    public void cargarDatos(){
        Usuario u = ac.leer(getApplication());
        if(u.getEmail() == "-1" && u.getContrasenia() == "-1"){
            noUser.setValue("");
        }else{
            user.setValue(u);
        }
    }
}
