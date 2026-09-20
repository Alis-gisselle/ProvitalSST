package com.provital.sst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.provital.sst.model.Persona;
import com.provital.sst.network.ApiConfig;

/** Equivalente Android nativo de personaDetalle.jsp. */
public class PersonaDetalleActivity extends AppCompatActivity {
    public static final String EXTRA_PERSONA = "com.provital.sst.EXTRA_PERSONA";

    private Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_detalle);

        persona = obtenerPersona();
        mostrarPersona();
        configurarAcciones();
    }

    private Persona obtenerPersona() {
        Persona recibida = (Persona) getIntent().getSerializableExtra(EXTRA_PERSONA);
        if (recibida != null) return recibida;

        // Datos de muestra para poder abrir y revisar la pantalla sin depender aún de la API.
        return new Persona(1, "María", "González", "4.567.890",
                "empleado", "Empresa Demo S.A.", "admisional");
    }

    private void mostrarPersona() {
        ((TextView) findViewById(R.id.tvTitulo)).setText(persona.getNombreCompleto());
        ((TextView) findViewById(R.id.tvNombre)).setText(persona.getNombreCompleto());
        ((TextView) findViewById(R.id.tvIniciales)).setText(obtenerIniciales());
        ((TextView) findViewById(R.id.tvCi)).setText(persona.getCi());
        ((TextView) findViewById(R.id.tvTipo)).setText(capitalizar(persona.getTipo()));
        ((TextView) findViewById(R.id.tvCategoria)).setText(
                persona.esAdmisional() ? "Admisional" : "Manipulador de alimentos");

        TextView empresa = findViewById(R.id.tvEmpresa);
        View filaEmpresa = findViewById(R.id.filaEmpresa);
        if (persona.getEmpresa() == null || persona.getEmpresa().trim().isEmpty()) {
            filaEmpresa.setVisibility(View.GONE);
        } else {
            empresa.setText(persona.getEmpresa());
        }

        // La ficha médica ocupacional solo aplica a admisionales, como en el JSP.
        findViewById(R.id.cardFicha).setVisibility(persona.esAdmisional() ? View.VISIBLE : View.GONE);
    }

    private void configurarAcciones() {
        findViewById(R.id.btnVolver).setOnClickListener(v -> finish());
        findViewById(R.id.btnFicha).setOnClickListener(v -> abrirModulo("ficha"));
        findViewById(R.id.btnEstudios).setOnClickListener(v -> abrirModulo("estudio"));
        findViewById(R.id.btnCertificado).setOnClickListener(v -> abrirModulo(
                persona.esAdmisional() ? "certifMed" : "certifManipulador"));
    }

    private void abrirModulo(String modulo) {
        String cookie = getSharedPreferences("sesion_provital", MODE_PRIVATE)
                .getString("cookie", null);
        if (cookie == null) {
            Toast.makeText(this, "La sesión expiró. Inicie sesión nuevamente.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        Intent intent = new Intent(this, WebModuloActivity.class);
        intent.putExtra(WebModuloActivity.EXTRA_URL,
                ApiConfig.BASE_URL + modulo + "?idPersona=" + persona.getIdPersona());
        intent.putExtra(WebModuloActivity.EXTRA_COOKIE, cookie);
        startActivity(intent);
    }

    private String obtenerIniciales() {
        return inicial(persona.getNombre()) + inicial(persona.getApellido());
    }

    private String inicial(String texto) {
        return texto == null || texto.trim().isEmpty() ? "" : texto.trim().substring(0, 1).toUpperCase();
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return "";
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}
