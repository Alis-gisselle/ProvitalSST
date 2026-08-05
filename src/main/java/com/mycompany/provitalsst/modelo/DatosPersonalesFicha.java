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
public class DatosPersonalesFicha {
    private int idFicha;
    private String sexo;
    private String estadoCivil;
    private LocalDate fum;
    private String area;
    private String antiguedadCargo;
    private String gradoFormacion;
    private String telefono;
    private String domicilio;
    private String contactoEmergencia;
    private String telefonoEmergencia;
    private Integer numHijos;
    private String edadesHijos;

    public DatosPersonalesFicha() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDate getFum() {
        return fum;
    }

    public void setFum(LocalDate fum) {
        this.fum = fum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAntiguedadCargo() {
        return antiguedadCargo;
    }

    public void setAntiguedadCargo(String antiguedadCargo) {
        this.antiguedadCargo = antiguedadCargo;
    }

    public String getGradoFormacion() {
        return gradoFormacion;
    }

    public void setGradoFormacion(String gradoFormacion) {
        this.gradoFormacion = gradoFormacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public Integer getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(Integer numHijos) {
        this.numHijos = numHijos;
    }

    public String getEdadesHijos() {
        return edadesHijos;
    }

    public void setEdadesHijos(String edadesHijos) {
        this.edadesHijos = edadesHijos;
    }
    
    
    
}
