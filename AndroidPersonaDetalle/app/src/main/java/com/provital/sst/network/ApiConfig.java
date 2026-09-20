package com.provital.sst.network;

/** Dirección del Tomcat donde está desplegado el WAR ProvitalSST. */
public final class ApiConfig {
    private ApiConfig() { }

    // Emulador Android: 10.0.2.2 apunta a la computadora anfitriona.
    // En un teléfono físico, reemplazar por la IP LAN o el dominio HTTPS del servidor.
    public static final String BASE_URL = "http://10.0.2.2:8080/ProvitalSST-1.0-SNAPSHOT/";
}
