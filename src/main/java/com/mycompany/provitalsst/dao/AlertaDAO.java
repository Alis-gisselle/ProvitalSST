/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Alerta;
import com.mycompany.provitalsst.modelo.Persona;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */
public class AlertaDAO {


    public int contarVencidos() {
        return contarPorCondicion("f_venc < CURDATE()");
    }

    public int contarProximos() {
        return contarPorCondicion("f_venc >= CURDATE() AND DATEDIFF(f_venc, CURDATE()) <= 30");
    }

    private int contarPorCondicion(String condicion) {
        String sql = "SELECT " +
            "(SELECT COUNT(*) FROM Certif_Med WHERE " + condicion + ") + " +
            "(SELECT COUNT(*) FROM CertifManipulador WHERE " + condicion + ") AS total";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int contarSinFicha() {
        String sql = "SELECT COUNT(*) FROM Persona p " +
                     "WHERE p.categoria = 'admisional' AND p.idPersona NOT IN (SELECT idPersona FROM Ficha)";
        return contarSimple(sql);
    }

    public int contarEstudiosPendientes() {
        String sql = "SELECT COUNT(*) FROM Persona p WHERE p.idPersona NOT IN (SELECT idPersona FROM Estudio)";
        return contarSimple(sql);
    }

    private int contarSimple(String sql) {
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Alerta> listarVencidos() {
        return listarCertificadosPorCondicion("f_venc < CURDATE()");
    }

    public List<Alerta> listarProximos() {
        return listarCertificadosPorCondicion("f_venc >= CURDATE() AND DATEDIFF(f_venc, CURDATE()) <= 30");
    }

    private List<Alerta> listarCertificadosPorCondicion(String condicion) {
        List<Alerta> lista = new ArrayList<>();
        String sql =
            "SELECT c.f_venc, p.nombre, p.apellido, 'Certificado Médico' AS tipoAlerta " +
            "FROM Certif_Med c JOIN Persona p ON c.idPersona = p.idPersona WHERE c." + condicion + " " +
            "UNION ALL " +
            "SELECT c.f_venc, p.nombre, p.apellido, 'Certificado Manipulador' AS tipoAlerta " +
            "FROM CertifManipulador c JOIN Persona p ON c.idPersona = p.idPersona WHERE c." + condicion + " " +
            "ORDER BY f_venc ASC";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alerta a = new Alerta();
                a.setFechaVenc(rs.getDate("f_venc").toLocalDate());
                a.setNombrePersona(rs.getString("nombre"));
                a.setApellidoPersona(rs.getString("apellido"));
                a.setTipoAlerta(rs.getString("tipoAlerta"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Persona> listarSinFicha() {
        String sql = "SELECT p.*, ec.Nombre AS nombreEmpresa FROM Persona p " +
                     "LEFT JOIN Empleado e ON p.idPersona=e.idPersona " +
                     "LEFT JOIN EmpresaCliente ec ON e.idEmpresaCliente=ec.idEmpresaCliente " +
                     "WHERE p.categoria = 'admisional' AND p.idPersona NOT IN (SELECT idPersona FROM Ficha)";
        return listarPersonas(sql);
    }

    public List<Persona> listarEstudiosPendientes() {
        String sql = "SELECT p.*, ec.Nombre AS nombreEmpresa FROM Persona p " + //gloria fijate que si vas a usar estos tipos de consulta siempre tenes que dejar un espacio antes que se cierre la doble comilla
                     "LEFT JOIN Empleado e ON p.idPersona=e.idPersona " + 
                     "LEFT JOIN EmpresaCliente ec ON e.idEmpresaCliente=ec.idEmpresaCliente " + 
                     "WHERE p.idPersona NOT IN (SELECT idPersona FROM Estudio)";
        return listarPersonas(sql);
    }

    private List<Persona> listarPersonas(String sql) {
        List<Persona> lista = new ArrayList<>();
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    //la alerta para rrhh
    public int contarVencidosPorEmpresa(int idEmpresa) {
        return contarPorCondicionYEmpresa("f_venc < CURDATE()", idEmpresa);
    }

    public int contarProximosPorEmpresa(int idEmpresa) {
        return contarPorCondicionYEmpresa("f_venc >= CURDATE() AND DATEDIFF(f_venc, CURDATE()) <= 30", idEmpresa);
    }

    private int contarPorCondicionYEmpresa(String condicion, int idEmpresa) {
        String sql = "SELECT " +
            "(SELECT COUNT(*) FROM Certif_Med c JOIN Empleado e ON c.idPersona = e.idPersona WHERE e.idEmpresaCliente = ? AND c." + condicion + ") + " +
            "(SELECT COUNT(*) FROM CertifManipulador c JOIN Empleado e ON c.idPersona = e.idPersona WHERE e.idEmpresaCliente = ? AND c." + condicion + ") AS total";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            ps.setInt(2, idEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // Listas detalladas filtradas por Empresa
    public List<Alerta> listarVencidosPorEmpresa(int idEmpresa) {
        return listarCertificadosPorCondicionYEmpresa("f_venc < CURDATE()", idEmpresa);
    }

    public List<Alerta> listarProximosPorEmpresa(int idEmpresa) {
        return listarCertificadosPorCondicionYEmpresa("f_venc >= CURDATE() AND DATEDIFF(f_venc, CURDATE()) <= 30", idEmpresa);
    }

    private List<Alerta> listarCertificadosPorCondicionYEmpresa(String condicion, int idEmpresa) {
        List<Alerta> lista = new ArrayList<>();
        String sql =
            "SELECT c.f_venc, p.nombre, p.apellido, 'Certificado Médico' AS tipoAlerta " +
            "FROM Certif_Med c " +
            "JOIN Persona p ON c.idPersona = p.idPersona " +
            "JOIN Empleado e ON p.idPersona = e.idPersona " +
            "WHERE e.idEmpresaCliente = ? AND c." + condicion + " " +
            "UNION ALL " +
            "SELECT c.f_venc, p.nombre, p.apellido, 'Certificado Manipulador' AS tipoAlerta " +
            "FROM CertifManipulador c " +
            "JOIN Persona p ON c.idPersona = p.idPersona " +
            "JOIN Empleado e ON p.idPersona = e.idPersona " +
            "WHERE e.idEmpresaCliente = ? AND c." + condicion + " " +
            "ORDER BY f_venc ASC";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            ps.setInt(2, idEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alerta a = new Alerta();
                    a.setFechaVenc(rs.getDate("f_venc").toLocalDate());
                    a.setNombrePersona(rs.getString("nombre"));
                    a.setApellidoPersona(rs.getString("apellido"));
                    a.setTipoAlerta(rs.getString("tipoAlerta"));
                    lista.add(a);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    // Contar personas sin ficha técnica filtrando por empresa
    public int contarSinFichaPorEmpresa(int idEmpresa) {
        String sql = "SELECT COUNT(*) FROM Persona p " +
                     "JOIN Empleado e ON p.idPersona = e.idPersona " +
                     "WHERE e.idEmpresaCliente = ? AND p.categoria = 'admisional' " +
                     "AND p.idPersona NOT IN (SELECT idPersona FROM Ficha)";
        return contarSimplePorEmpresa(sql, idEmpresa);
    }

    // Contar personas con estudios/informes pendientes filtrando por empresa
    public int contarEstudiosPendientesPorEmpresa(int idEmpresa) {
        String sql = "SELECT COUNT(*) FROM Persona p " +
                     "JOIN Empleado e ON p.idPersona = e.idPersona " +
                     "WHERE e.idEmpresaCliente = ? " +
                     "AND p.idPersona NOT IN (SELECT idPersona FROM Estudio)";
        return contarSimplePorEmpresa(sql, idEmpresa);
    }

    // Auxiliar para conteos simples con filtro de idEmpresa
    private int contarSimplePorEmpresa(String sql, int idEmpresa) {
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    // Listar personas sin ficha técnica por empresa
    public List<Persona> listarSinFichaPorEmpresa(int idEmpresa) {
        String sql = "SELECT p.*, ec.Nombre AS nombreEmpresa FROM Persona p " +
                     "JOIN Empleado e ON p.idPersona = e.idPersona " +
                     "LEFT JOIN EmpresaCliente ec ON e.idEmpresaCliente = ec.idEmpresaCliente " +
                     "WHERE e.idEmpresaCliente = ? AND p.categoria = 'admisional' " +
                     "AND p.idPersona NOT IN (SELECT idPersona FROM Ficha)";
        return listarPersonasPorEmpresa(sql, idEmpresa);
    }

    // Listar personas con estudios pendientes por empresa
    public List<Persona> listarEstudiosPendientesPorEmpresa(int idEmpresa) {
        String sql = "SELECT p.*, ec.Nombre AS nombreEmpresa FROM Persona p " +
                     "JOIN Empleado e ON p.idPersona = e.idPersona " +
                     "LEFT JOIN EmpresaCliente ec ON e.idEmpresaCliente = ec.idEmpresaCliente " +
                     "WHERE e.idEmpresaCliente = ? " +
                     "AND p.idPersona NOT IN (SELECT idPersona FROM Estudio)";
        return listarPersonasPorEmpresa(sql, idEmpresa);
    }

    // Auxiliar para listar personas pasando el parámetro idEmpresa
    private List<Persona> listarPersonasPorEmpresa(String sql, int idEmpresa) {
        List<Persona> lista = new ArrayList<>();
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
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