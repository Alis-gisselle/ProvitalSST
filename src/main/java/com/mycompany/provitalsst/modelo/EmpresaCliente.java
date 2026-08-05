/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class EmpresaCliente {
    private int idEmpresaCliente;
    private int ruc;
    private String nombre;
    private String direccion;

    public EmpresaCliente() {
    }

    public EmpresaCliente(int idEmpresaCliente, int ruc, String nombre, String direccion) {
        this.idEmpresaCliente = idEmpresaCliente;
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getIdEmpresaCliente() {
        return idEmpresaCliente;
    }

    public void setIdEmpresaCliente(int idEmpresaCliente) {
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
