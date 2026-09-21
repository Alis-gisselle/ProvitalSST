package com.provital.sst;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.provital.sst.model.Persona;
import com.provital.sst.network.ApiConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

/** Pantalla Android nativa para ficha, estudios y certificados. No carga JSP. */
public class HistorialActivity extends AppCompatActivity {
    public static final String EXTRA_PERSONA = "persona";
    public static final String EXTRA_MODULO = "modulo";
    private LinearLayout contenedor;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_historial);
        Persona persona = (Persona) getIntent().getSerializableExtra(EXTRA_PERSONA);
        String modulo = getIntent().getStringExtra(EXTRA_MODULO);
        if (persona == null || modulo == null) { finish(); return; }
        ((TextView) findViewById(R.id.tvHistorialTitulo)).setText(titulo(modulo) + "\n" + persona.getNombreCompleto());
        findViewById(R.id.btnRegresar).setOnClickListener(v -> finish());
        contenedor = findViewById(R.id.contenedorRegistros);
        cargarHistorial(persona.getIdPersona(), modulo);
    }

    private String titulo(String modulo) {
        if ("ficha".equals(modulo)) return "Fichas médicas";
        if ("estudio".equals(modulo)) return "Estudios";
        return "Certificados";
    }

    private void cargarHistorial(int idPersona, String modulo) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String cookie = getSharedPreferences("sesion_provital", MODE_PRIVATE).getString("cookie", "");
                URL url = new URL(ApiConfig.BASE_URL + "api/historial?idPersona=" + idPersona + "&modulo=" + modulo);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestProperty("Cookie", cookie);
                c.setConnectTimeout(10000); c.setReadTimeout(10000);
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) throw new IOException("No autorizado");
                StringBuilder json = new StringBuilder();
                try (BufferedReader lector = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8))) {
                    String linea; while ((linea = lector.readLine()) != null) json.append(linea);
                }
                JSONArray items = new JSONObject(json.toString()).getJSONArray("items");
                runOnUiThread(() -> mostrarItems(items));
            } catch (Exception ex) {
                runOnUiThread(() -> Toast.makeText(this, "No se pudo cargar el historial", Toast.LENGTH_LONG).show());
            }
        });
    }

    private void mostrarItems(JSONArray items) {
        findViewById(R.id.pbCarga).setVisibility(View.GONE);
        if (items.length() == 0) {
            ((TextView) findViewById(R.id.tvVacio)).setVisibility(View.VISIBLE);
            return;
        }
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            TextView registro = new TextView(this);
            registro.setBackgroundResource(R.drawable.bg_card);
            registro.setElevation(2);
            registro.setPadding(20, 18, 20, 18);
            registro.setText(item.optString("fecha") + "\n" + item.optString("titulo") + "\n" + item.optString("detalle"));
            registro.setTextSize(16);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
            p.setMargins(0, 0, 0, 14);
            contenedor.addView(registro, p);
        }
    }
}
