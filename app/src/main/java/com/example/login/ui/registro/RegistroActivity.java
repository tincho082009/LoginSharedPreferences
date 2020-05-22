package com.example.login.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private EditText etDni, etApellido, etNombre, etEmail, etContrasenia;
    private Button btnGuardar;
    private RegistroViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        vm.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni() + "");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etEmail.setText(usuario.getEmail());
                etContrasenia.setText(usuario.getContrasenia());
            }
        });
        vm.getNoUser().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etDni.setText(s);
                etApellido.setText(s);
                etNombre.setText(s);
                etEmail.setText(s);
                etContrasenia.setText(s);
            }
        });
        configView();

    }
    public void configView(){
        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmailRegistro);
        etContrasenia = findViewById(R.id.etContraRegistro);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Datos guardados correctamente", Toast.LENGTH_LONG).show();
            }
        });
        vm.cargarDatos();

    }
}
