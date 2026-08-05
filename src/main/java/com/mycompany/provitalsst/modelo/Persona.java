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
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private int ci;
    private LocalDate fechaNacimiento;
    private String categoria; // ahi tiene la opcion de admisional o manipulador

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellido, int ci, LocalDate fechaNacimiento, String categoria) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.fechaNacimiento = fechaNacimiento;
        this.categoria = categoria;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
