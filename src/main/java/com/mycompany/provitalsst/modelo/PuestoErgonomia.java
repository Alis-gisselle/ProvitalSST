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
public class PuestoErgonomia {
    private int idFicha;
    private String descripcionGeneral;
    private String tareasPrincipales;
    private String tipoActividad;
    private String esfuerzoFisico;
    private boolean levantaCargas;
    private BigDecimal pesoAprox;

    public PuestoErgonomia() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getTareasPrincipales() {
        return tareasPrincipales;
    }

    public void setTareasPrincipales(String tareasPrincipales) {
        this.tareasPrincipales = tareasPrincipales;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getEsfuerzoFisico() {
        return esfuerzoFisico;
    }

    public void setEsfuerzoFisico(String esfuerzoFisico) {
        this.esfuerzoFisico = esfuerzoFisico;
    }

    public boolean isLevantaCargas() {
        return levantaCargas;
    }

    public void setLevantaCargas(boolean levantaCargas) {
        this.levantaCargas = levantaCargas;
    }

    public BigDecimal getPesoAprox() {
        return pesoAprox;
    }

    public void setPesoAprox(BigDecimal pesoAprox) {
        this.pesoAprox = pesoAprox;
    }
    
}
