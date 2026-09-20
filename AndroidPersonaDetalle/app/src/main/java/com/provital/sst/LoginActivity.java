package com.provital.sst;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.provital.sst.model.Persona;
import com.provital.sst.network.ApiConfig;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        autenticarEnServidor(correo, contrasena);
    }

    private void autenticarEnServidor(String correo, String contrasena) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            HttpURLConnection conexion = null;
            try {
                URL url = new URL(ApiConfig.BASE_URL + "api/login");
                conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setConnectTimeout(10000);
                conexion.setReadTimeout(10000);
                conexion.setDoOutput(true);
                conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                String cuerpo = "correo=" + URLEncoder.encode(correo, StandardCharsets.UTF_8.name())
                        + "&contrasenia=" + URLEncoder.encode(contrasena, StandardCharsets.UTF_8.name());
                try (BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(
                        conexion.getOutputStream(), StandardCharsets.UTF_8))) {
                    salida.write(cuerpo);
                }
                int codigo = conexion.getResponseCode();
                String cookie = obtenerCookie(conexion.getHeaderFields());
                runOnUiThread(() -> procesarRespuesta(codigo, cookie));
            } catch (IOException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudo conectar al servidor. Verifique ApiConfig.BASE_URL.", Toast.LENGTH_LONG).show());
            } finally {
                if (conexion != null) conexion.disconnect();
                executor.shutdown();
            }
        });
    }

    private String obtenerCookie(Map<String, List<String>> cabeceras) {
        List<String> cookies = cabeceras.get("Set-Cookie");
        if (cookies == null || cookies.isEmpty()) return null;
        return cookies.get(0).split(";", 2)[0];
    }

    private void procesarRespuesta(int codigo, String cookie) {
        if (codigo == HttpURLConnection.HTTP_OK && cookie != null) {
            getSharedPreferences("sesion_provital", MODE_PRIVATE).edit()
                    .putString("cookie", cookie).apply();
            Persona demo = new Persona(1, "María", "González", "4.567.890",
                    "empleado", "Empresa Demo S.A.", "admisional");
            Intent intent = new Intent(this, PersonaDetalleActivity.class);
            intent.putExtra(PersonaDetalleActivity.EXTRA_PERSONA, demo);
            startActivity(intent);
            finish();
        } else if (codigo == HttpURLConnection.HTTP_UNAUTHORIZED) {
            tilContrasena.setError("Correo o contraseña incorrectos");
        } else {
            Toast.makeText(this, "Error al iniciar sesión (" + codigo + ")", Toast.LENGTH_LONG).show();
        }
    }
}
