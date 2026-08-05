/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

import java.math.BigDecimal;

/**
 *
 * @author alis
 */
public class ExamenFisico {
    private int idFicha;
    private BigDecimal peso;
    private BigDecimal estatura;
    private String presionArterial;
    private Integer frecuenciaCardiaca;
    private String agudezaVisualDerecho;
    private String agudezaVisualIzquierdo;

    public ExamenFisico() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getEstatura() {
        return estatura;
    }

    public void setEstatura(BigDecimal estatura) {
        this.estatura = estatura;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getAgudezaVisualDerecho() {
        return agudezaVisualDerecho;
    }

    public void setAgudezaVisualDerecho(String agudezaVisualDerecho) {
        this.agudezaVisualDerecho = agudezaVisualDerecho;
    }

    public String getAgudezaVisualIzquierdo() {
        return agudezaVisualIzquierdo;
    }

    public void setAgudezaVisualIzquierdo(String agudezaVisualIzquierdo) {
        this.agudezaVisualIzquierdo = agudezaVisualIzquierdo;
    }
    
}
