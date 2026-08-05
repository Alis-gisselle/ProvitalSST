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
public class CertifManipulador {
    private int idCertifManipulador;
    private String aptitud;
    private String recomendaciones;
    private LocalDate fechaEmision;
    private LocalDate fechaVenc;
    private int idPersona;
    private int idMedicoLaboral;
    private String nombreMedico;

    public CertifManipulador() {
    }

    public int getIdCertifManipulador() {
        return idCertifManipulador;
    }

    public void setIdCertifManipulador(int idCertifManipulador) {
        this.idCertifManipulador = idCertifManipulador;
    }

    public String getAptitud() {
        return aptitud;
    }

    public void setAptitud(String aptitud) {
        this.aptitud = aptitud;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(LocalDate fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdMedicoLaboral() {
        return idMedicoLaboral;
    }

    public void setIdMedicoLaboral(int idMedicoLaboral) {
        this.idMedicoLaboral = idMedicoLaboral;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }
    
    
}
