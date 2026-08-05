/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class MedicoLaboral {
    private int idMedicoLaboral;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String matricula;
    private int idUsuario;
    private String correo;

    public MedicoLaboral() {
    }

    public MedicoLaboral(int idMedicoLaboral, String nombre, String apellido, String especialidad, String matricula, int idUsuario, String correo) {
        this.idMedicoLaboral = idMedicoLaboral;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.idUsuario = idUsuario;
        this.correo = correo;
    }

    public int getIdMedicoLaboral() {
        return idMedicoLaboral;
    }

    public void setIdMedicoLaboral(int idMedicoLaboral) {
        this.idMedicoLaboral = idMedicoLaboral;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
