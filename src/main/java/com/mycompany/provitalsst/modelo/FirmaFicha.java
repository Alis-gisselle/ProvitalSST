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
public class FirmaFicha {
    private int idFicha;
    private String ciTrabajador;
    private LocalDate fechaFirmaTrabajador;
    private String ciTecnico;
    private LocalDate fechaFirmaTecnico;

    public FirmaFicha() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getCiTrabajador() {
        return ciTrabajador;
    }

    public void setCiTrabajador(String ciTrabajador) {
        this.ciTrabajador = ciTrabajador;
    }

    public LocalDate getFechaFirmaTrabajador() {
        return fechaFirmaTrabajador;
    }

    public void setFechaFirmaTrabajador(LocalDate fechaFirmaTrabajador) {
        this.fechaFirmaTrabajador = fechaFirmaTrabajador;
    }

    public String getCiTecnico() {
        return ciTecnico;
    }

    public void setCiTecnico(String ciTecnico) {
        this.ciTecnico = ciTecnico;
    }

    public LocalDate getFechaFirmaTecnico() {
        return fechaFirmaTecnico;
    }

    public void setFechaFirmaTecnico(LocalDate fechaFirmaTecnico) {
        this.fechaFirmaTecnico = fechaFirmaTecnico;
    }
    
}
