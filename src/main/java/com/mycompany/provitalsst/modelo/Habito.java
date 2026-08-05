/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class Habito {
    private int idFicha;
    private boolean fuma;
    private boolean consumeAlcohol;
    private boolean actividadFisica;
    private boolean suenoAdecuado;
    private boolean otrosHabitos;
    private String observaciones;

    public Habito () {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public boolean isConsumeAlcohol() {
        return consumeAlcohol;
    }

    public void setConsumeAlcohol(boolean consumeAlcohol) {
        this.consumeAlcohol = consumeAlcohol;
    }

    public boolean isActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(boolean actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public boolean isSuenoAdecuado() {
        return suenoAdecuado;
    }

    public void setSuenoAdecuado(boolean suenoAdecuado) {
        this.suenoAdecuado = suenoAdecuado;
    }

    public boolean isOtrosHabitos() {
        return otrosHabitos;
    }

    public void setOtrosHabitos(boolean otrosHabitos) {
        this.otrosHabitos = otrosHabitos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
