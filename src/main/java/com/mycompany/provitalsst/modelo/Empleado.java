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
public class Empleado extends Persona {
    private int idEmpresaCliente;
    private String cargo;

    public Empleado() {
    }

    public Empleado(int idEmpresaCliente, String cargo, int idPersona, String nombre, String apellido, int ci, LocalDate fechaNacimiento, String categoria) {
        super(idPersona, nombre, apellido, ci, fechaNacimiento, categoria);
        this.idEmpresaCliente = idEmpresaCliente;
        this.cargo = cargo;
    }

    public int getIdEmpresaCliente() {
        return idEmpresaCliente;
    }

    public void setIdEmpresaCliente(int idEmpresaCliente) {
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
