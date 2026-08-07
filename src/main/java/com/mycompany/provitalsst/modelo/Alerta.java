/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

import java.time.LocalDate;

/**
 *
 * @author alis
 */
public class Alerta {
    int idAlertaVenc;
    private Integer idCertifMed;
    private Integer idCertifManipulador;
    private LocalDate fechaGenerada;
    private LocalDate fechaVenc;
    private String estado;
    private String nombrePersona;
    private String apellidoPersona;
    private String tipoAlerta;

    public Alerta() {
    }

    public int getIdAlertaVenc() {
        return idAlertaVenc;
    }

    public void setIdAlertaVenc(int idAlertaVenc) {
        this.idAlertaVenc = idAlertaVenc;
    }

    public Integer getIdCertifMed() {
        return idCertifMed;
    }

    public void setIdCertifMed(Integer idCertifMed) {
        this.idCertifMed = idCertifMed;
    }

    public Integer getIdCertifManipulador() {
        return idCertifManipulador;
    }

    public void setIdCertifManipulador(Integer idCertifManipulador) {
        this.idCertifManipulador = idCertifManipulador;
    }

    public LocalDate getFechaGenerada() {
        return fechaGenerada;
    }

    public void setFechaGenerada(LocalDate fechaGenerada) {
        this.fechaGenerada = fechaGenerada;
    }

    public LocalDate getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(LocalDate fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }
    
    
}
