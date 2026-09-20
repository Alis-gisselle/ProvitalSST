package com.provital.sst.model;

import java.io.Serializable;

/** Datos que la pantalla PersonaDetalle necesita recibir desde el listado o la API. */
public class Persona implements Serializable {
    private final int idPersona;
    private final String nombre;
    private final String apellido;
    private final String ci;
    private final String tipo;
    private final String empresa;
    private final String categoria;

    public Persona(int idPersona, String nombre, String apellido, String ci,
                   String tipo, String empresa, String categoria) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.tipo = tipo;
        this.empresa = empresa;
        this.categoria = categoria;
    }

    public int getIdPersona() { return idPersona; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCi() { return ci; }
    public String getTipo() { return tipo; }
    public String getEmpresa() { return empresa; }
    public String getCategoria() { return categoria; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
    public boolean esAdmisional() { return "admisional".equalsIgnoreCase(categoria); }
}
