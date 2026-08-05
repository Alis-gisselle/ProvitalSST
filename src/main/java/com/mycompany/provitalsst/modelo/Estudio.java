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
public class Estudio {
    private int idEstudio;
    private LocalDate fecha;
    private String archivoPdf;
    private int idPersona;
    private int idMedicoLaboral;
    private String nombreMedico;

    public Estudio() {
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(String archivoPdf) {
        this.archivoPdf = archivoPdf;
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
