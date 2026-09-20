/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.CertifManipulador;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */

public class CertifManipuladorDAO {

    public boolean insertar(CertifManipulador c) {
        LocalDate fVenc = c.getFechaEmision().plusYears(1);

        String sql = "INSERT INTO certifmanipulador (aptitud, recomendaciones, f_emision, f_venc, idPersona, idMedicoLaboral) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getAptitud());
            ps.setString(2, c.getRecomendaciones());
            ps.setDate(3, Date.valueOf(c.getFechaEmision()));
            ps.setDate(4, Date.valueOf(fVenc));
            ps.setInt(5, c.getIdPersona());
            ps.setInt(6, c.getIdMedicoLaboral());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idCertifManipulador) {
        String sql = "DELETE FROM certifmanipulador WHERE idCertifManipulador = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCertifManipulador);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public CertifManipulador buscarPorId(int idCertifManipulador) {
        String sql = "SELECT c.*, m.nombre AS nombreMedico, m.apellido AS apellidoMedico " +
                     "FROM certifmanipulador c JOIN medicolaboral m ON c.idMedicoLaboral = m.idMedicoLaboral " +
                     "WHERE c.idCertifManipulador = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCertifManipulador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<CertifManipulador> listarPorPersona(int idPersona) {
        List<CertifManipulador> lista = new ArrayList<>();
        String sql = "SELECT c.*, m.nombre AS nombreMedico, m.apellido AS apellidoMedico " +
                     "FROM certifmanipulador c JOIN medicolaboral m ON c.idMedicoLaboral = m.idMedicoLaboral " +
                     "WHERE c.idPersona = ? ORDER BY c.f_emision DESC";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private CertifManipulador mapear(ResultSet rs) throws SQLException {
        CertifManipulador c = new CertifManipulador();
        c.setIdCertifManipulador(rs.getInt("idCertifManipulador"));
        c.setAptitud(rs.getString("aptitud"));
        c.setRecomendaciones(rs.getString("recomendaciones"));
        c.setFechaEmision(rs.getDate("f_emision").toLocalDate());
        c.setFechaVenc(rs.getDate("f_venc").toLocalDate());
        c.setIdPersona(rs.getInt("idPersona"));
        c.setIdMedicoLaboral(rs.getInt("idMedicoLaboral"));
        c.setNombreMedico(rs.getString("nombreMedico") + " " + rs.getString("apellidoMedico"));
        return c;
    }
}
