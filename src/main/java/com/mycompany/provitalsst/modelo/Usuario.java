/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class Usuario {
    private int idUsuario;
    private String rol;
    private String correo;
    private String contrasenia;
    private Integer idEmpresaCliente;//Gloria puse integer porque puede ser null

    public Usuario() {
    }

    public Usuario(int idUsuario, String rol, String correo, String contrasenia, Integer idEmpresaCliente) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getIdEmpresaCliente() {
        return idEmpresaCliente;
    }

    public void setIdEmpresaCliente(Integer idEpresaCliente) {
        this.idEmpresaCliente = idEpresaCliente;
    }

    
    

}