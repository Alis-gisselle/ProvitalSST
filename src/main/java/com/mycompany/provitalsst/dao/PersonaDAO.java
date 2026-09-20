/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public Persona buscarPorId(int idPersona) {
        String sql = "SELECT * FROM persona WHERE idPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Persona p = new Persona();
                p.setIdPersona(rs.getInt("idPersona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setCi(rs.getInt("ci"));
                p.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                p.setCategoria(rs.getString("categoria"));
                return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Devuelve "empleado", "colaborador", o null si no se encuentra en ninguna
    public String determinarTipo(int idPersona) {
        String sqlEmpleado = "SELECT idPersona FROM empleado WHERE idPersona = ?";
        String sqlColaborador = "SELECT idPersona FROM colaborador WHERE idPersona = ?";
        try (Connection con = Conexion.conectar()) {
            try (PreparedStatement ps = con.prepareStatement(sqlEmpleado)) {
                ps.setInt(1, idPersona);
                if (ps.executeQuery().next()) return "empleado";
            }
            try (PreparedStatement ps = con.prepareStatement(sqlColaborador)) {
                ps.setInt(1, idPersona);
                if (ps.executeQuery().next()) return "colaborador";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Nombre de la empresa, solo si es empleado
    public String obtenerNombreEmpresa(int idPersona) {
        String sql = "SELECT ec.Nombre FROM empleado e JOIN empresacliente ec ON e.idEmpresaCliente = ec.idEmpresaCliente WHERE e.idPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("Nombre");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Persona> listarTodosLosPacientesGlobal() {

    List<Persona> lista = new ArrayList<>();

    String sql = """
        SELECT idPersona, nombre, apellido, ci, 
               fechaNacimiento, categoria
        FROM persona
        ORDER BY apellido ASC
        """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Persona p = new Persona();

            p.setIdPersona(rs.getInt("idPersona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellido(rs.getString("apellido"));
            p.setCi(rs.getInt("ci"));

            Date fecha = rs.getDate("fechaNacimiento");
            if (fecha != null) {
                p.setFechaNacimiento(fecha.toLocalDate());
            }

            if (fecha != null) {
                p.setFechaNacimiento(fecha.toLocalDate());
            }

            p.setCategoria(rs.getString("categoria"));

            // Buscar qué tipo de persona es
            String tipo = determinarTipo(p.getIdPersona());

            if ("empleado".equals(tipo)) {
                p.setNombreEmpresa(
                    obtenerNombreEmpresa(p.getIdPersona())
                );
            } else {
                p.setNombreEmpresa("Sin Empresa");
            }

            lista.add(p);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return lista;
}
   public List<Persona> buscarPacientesGlobal(String criterio) { 
    List<Persona> lista = new ArrayList<>(); 
    String sql = "SELECT p.idPersona, p.nombre, p.apellido, p.ci, p.categoria, ec.Nombre AS nombreEmpresa " + 
                 "FROM persona p " +
                 "LEFT JOIN empleado e ON p.idPersona = e.idPersona " +
                 "LEFT JOIN empresacliente ec ON e.idEmpresaCliente = ec.idEmpresaCliente " +
                 "WHERE p.nombre LIKE ? OR p.apellido LIKE ? OR CAST(p.ci AS CHAR) LIKE ? " + 
                 "ORDER BY p.apellido ASC"; 

    try (Connection con = Conexion.conectar(); 
         PreparedStatement ps = con.prepareStatement(sql)) { 

        String filtro = "%" + criterio + "%"; 
        ps.setString(1, filtro); 
        ps.setString(2, filtro); 
        ps.setString(3, filtro); 

        try (ResultSet rs = ps.executeQuery()) { 
            while (rs.next()) { 
                Persona p = new Persona(); 
                p.setIdPersona(rs.getInt("idPersona")); 
                p.setNombre(rs.getString("nombre")); 
                p.setApellido(rs.getString("apellido")); 
                p.setCi(rs.getInt("ci")); 
                p.setCategoria(rs.getString("categoria")); 
                p.setNombreEmpresa(rs.getString("nombreEmpresa")); 
                lista.add(p); 
            } 
        } 
    } catch (SQLException ex) { 
        ex.printStackTrace(); 
    } 
    return lista; 
}
}
