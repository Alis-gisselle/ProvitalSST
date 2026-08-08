/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

import java.util.List;

/**
 *
 * @author alis
 */
public class FichaCompleta {
    private Ficha ficha;
    private Persona persona;
    private String cargo; 
    private String nombreEmpresa; 
    private DatosPersonalesFicha datosPersonales;
    private AntecedentePersonal antecedentePersonal;
    private Habito habito;
    private AntecedenteLaboral antecedenteLaboral;
    private PuestoErgonomia puestoErgonomia;
    private List<RiesgoLaboral> riesgos;
    private EPP epp;
    private TrastornoMusculo trastornoMusculo;
    private List<ZonaAfectada> zonas;
    private ExamenFisico examenFisico;
    private FirmaFicha firma;

    public FichaCompleta() {
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public DatosPersonalesFicha getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonalesFicha datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public AntecedentePersonal getAntecedentePersonal() {
        return antecedentePersonal;
    }

    public void setAntecedentePersonal(AntecedentePersonal antecedentePersonal) {
        this.antecedentePersonal = antecedentePersonal;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public AntecedenteLaboral getAntecedenteLaboral() {
        return antecedenteLaboral;
    }

    public void setAntecedenteLaboral(AntecedenteLaboral antecedenteLaboral) {
        this.antecedenteLaboral = antecedenteLaboral;
    }

    public PuestoErgonomia getPuestoErgonomia() {
        return puestoErgonomia;
    }

    public void setPuestoErgonomia(PuestoErgonomia puestoErgonomia) {
        this.puestoErgonomia = puestoErgonomia;
    }

    public List<RiesgoLaboral> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<RiesgoLaboral> riesgos) {
        this.riesgos = riesgos;
    }

    public EPP getEpp() {
        return epp;
    }

    public void setEpp(EPP epp) {
        this.epp = epp;
    }

    public TrastornoMusculo getTrastornoMusculo() {
        return trastornoMusculo;
    }

    public void setTrastornoMusculo(TrastornoMusculo trastornoMusculo) {
        this.trastornoMusculo = trastornoMusculo;
    }

    public List<ZonaAfectada> getZonas() {
        return zonas;
    }

    public void setZonas(List<ZonaAfectada> zonas) {
        this.zonas = zonas;
    }

    public ExamenFisico getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(ExamenFisico examenFisico) {
        this.examenFisico = examenFisico;
    }

    public FirmaFicha getFirma() {
        return firma;
    }

    public void setFirma(FirmaFicha firma) {
        this.firma = firma;
    }
    
}
