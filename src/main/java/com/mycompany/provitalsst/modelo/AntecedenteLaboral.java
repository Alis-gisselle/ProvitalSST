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
public class AntecedenteLaboral {
    private int idFicha;
    private String empresaAnterior;
    private String puestoAnterior;
    private LocalDate periodoDesde;
    private LocalDate periodoHasta;

    public AntecedenteLaboral() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getEmpresaAnterior() {
        return empresaAnterior;
    }

    public void setEmpresaAnterior(String empresaAnterior) {
        this.empresaAnterior = empresaAnterior;
    }

    public String getPuestoAnterior() {
        return puestoAnterior;
    }

    public void setPuestoAnterior(String puestoAnterior) {
        this.puestoAnterior = puestoAnterior;
    }

    public LocalDate getPeriodoDesde() {
        return periodoDesde;
    }

    public void setPeriodoDesde(LocalDate periodoDesde) {
        this.periodoDesde = periodoDesde;
    }

    public LocalDate getPeriodoHasta() {
        return periodoHasta;
    }

    public void setPeriodoHasta(LocalDate periodoHasta) {
        this.periodoHasta = periodoHasta;
    }
    
}
