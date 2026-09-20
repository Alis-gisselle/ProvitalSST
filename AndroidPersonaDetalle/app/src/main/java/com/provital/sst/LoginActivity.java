package com.provital.sst;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.provital.sst.model.Persona;

/** Pantalla inicial de autenticación de la aplicación. */
public class LoginActivity extends AppCompatActivity {
    private EditText etCorreo;
    private EditText etContrasena;
    private TextInputLayout tilCorreo;
    private TextInputLayout tilContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        tilCorreo = findViewById(R.id.tilCorreo);
        tilContrasena = findViewById(R.id.tilContrasena);
        MaterialButton btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(v -> iniciarSesion());
    }

    private void iniciarSesion() {
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString();
        tilCorreo.setError(null);
        tilContrasena.setError(null);

        boolean valido = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Ingrese un correo válido");
            valido = false;
        }
        if (contrasena.length() < 6) {
            tilContrasena.setError("La contraseña debe tener al menos 6 caracteres");
            valido = false;
        }
        if (!valido) return;

        // Punto de integración: reemplazar por POST /login al publicar la API del sistema web.
        getSharedPreferences("sesion_provital", MODE_PRIVATE)
                .edit().putString("correo", correo).apply();

        Persona demo = new Persona(1, "María", "González", "4.567.890",
                "empleado", "Empresa Demo S.A.", "admisional");
        Intent intent = new Intent(this, PersonaDetalleActivity.class);
        intent.putExtra(PersonaDetalleActivity.EXTRA_PERSONA, demo);
        startActivity(intent);
        finish();
    }
}
