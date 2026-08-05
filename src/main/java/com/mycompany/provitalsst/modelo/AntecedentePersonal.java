/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class AntecedentePersonal {
    private int idFicha;
    private boolean enfermedades;
    private boolean cirugias;
    private boolean medicamentos;
    private boolean alergias;
    private boolean otrosRelevantes;
    private String observaciones;

    public AntecedentePersonal() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public boolean isEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(boolean enfermedades) {
        this.enfermedades = enfermedades;
    }

    public boolean isCirugias() {
        return cirugias;
    }

    public void setCirugias(boolean cirugias) {
        this.cirugias = cirugias;
    }

    public boolean isMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean isAlergias() {
        return alergias;
    }

    public void setAlergias(boolean alergias) {
        this.alergias = alergias;
    }

    public boolean isOtrosRelevantes() {
        return otrosRelevantes;
    }

    public void setOtrosRelevantes(boolean otrosRelevantes) {
        this.otrosRelevantes = otrosRelevantes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
