package com.example.login.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.login.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp==null){
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }
    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("email", usuario.getEmail());
        editor.putString("contrasenia", usuario.getContrasenia());
        editor.commit();
    }
    public static Usuario leer(Context context){
        sp = conectar(context);
        Long dni = sp.getLong("dni", 0);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String email = sp.getString("email", "-1");
        String contrasenia = sp.getString("contrasenia", "-1");
        Usuario usuario = new Usuario(dni, apellido, nombre, email, contrasenia);
        return usuario;
    }

    public static Usuario login(Context context, String mail, String contra){
        Usuario user = null;
        sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String email = sp.getString("email", "-1");
        String contrasenia = sp.getString("contrasenia", "-1");
        if(mail.equals(email) && contra.equals(contrasenia)){
            user = new Usuario(dni, apellido, nombre, email, contrasenia);
        }
        return user;
    }
}
