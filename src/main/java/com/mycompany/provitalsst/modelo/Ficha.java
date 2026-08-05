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

public class Ficha {
    private int idFicha;
    private String numeroFicha;
    private LocalDate fecha;
    private String tipoEvaluacion;
    private int idPersona;

    public Ficha() {
    }

    public int getIdFicha() { return idFicha; }
    public void setIdFicha(int idFicha) { this.idFicha = idFicha; }
    public String getNumeroFicha() { return numeroFicha; }
    public void setNumeroFicha(String numeroFicha) { this.numeroFicha = numeroFicha; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipoEvaluacion() { return tipoEvaluacion; }
    public void setTipoEvaluacion(String tipoEvaluacion) { this.tipoEvaluacion = tipoEvaluacion; }
    public int getIdPersona() { return idPersona; }
    public void setIdPersona(int idPersona) { this.idPersona = idPersona; }
}
