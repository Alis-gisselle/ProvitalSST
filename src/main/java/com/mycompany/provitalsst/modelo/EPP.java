/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class EPP {
    private int idFicha;
    private boolean tapaboca;
    private boolean proteccionAuditiva;
    private boolean casco;
    private boolean gafas;
    private boolean botas;
    private boolean guantes;
    private boolean delantal;
    private String otros;

    public EPP() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public boolean isTapaboca() {
        return tapaboca;
    }

    public void setTapaboca(boolean tapaboca) {
        this.tapaboca = tapaboca;
    }

    public boolean isProteccionAuditiva() {
        return proteccionAuditiva;
    }

    public void setProteccionAuditiva(boolean proteccionAuditiva) {
        this.proteccionAuditiva = proteccionAuditiva;
    }

    public boolean isCasco() {
        return casco;
    }

    public void setCasco(boolean casco) {
        this.casco = casco;
    }

    public boolean isGafas() {
        return gafas;
    }

    public void setGafas(boolean gafas) {
        this.gafas = gafas;
    }

    public boolean isBotas() {
        return botas;
    }

    public void setBotas(boolean botas) {
        this.botas = botas;
    }

    public boolean isGuantes() {
        return guantes;
    }

    public void setGuantes(boolean guantes) {
        this.guantes = guantes;
    }

    public boolean isDelantal() {
        return delantal;
    }

    public void setDelantal(boolean delantal) {
        this.delantal = delantal;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }
    
    
}
