/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.CertifMed;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */

public class CertifMedDAO {

    public boolean insertar(CertifMed c) {
        LocalDate fVenc = c.getFechaEmision().plusMonths(12);

        String sql = "INSERT INTO Certif_Med (tipoEvaluacion, aptitud, recomendacion, observaciones, f_emision, f_venc, idPersona, idMedicoLaboral) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getTipoEvaluacion());
            ps.setString(2, c.getAptitud());
            ps.setString(3, c.getRecomendacion());
            ps.setString(4, c.getObservaciones());
            ps.setDate(5, Date.valueOf(c.getFechaEmision()));
            ps.setDate(6, Date.valueOf(fVenc));
            ps.setInt(7, c.getIdPersona());
            ps.setInt(8, c.getIdMedicoLaboral());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idCertifMed) {
        String sql = "DELETE FROM Certif_Med WHERE idCertif_Med = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCertifMed);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<CertifMed> listarPorPersona(int idPersona) {
        List<CertifMed> lista = new ArrayList<>();
        String sql = "SELECT c.*, m.nombre AS nombreMedico, m.apellido AS apellidoMedico " +
                     "FROM Certif_Med c JOIN MedicoLaboral m ON c.idMedicoLaboral = m.idMedicoLaboral " +
                     "WHERE c.idPersona = ? ORDER BY c.f_emision DESC";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private CertifMed mapear(ResultSet rs) throws SQLException {
        CertifMed c = new CertifMed();
        c.setIdCertifMed(rs.getInt("idCertif_Med"));
        c.setTipoEvaluacion(rs.getString("tipoEvaluacion"));
        c.setAptitud(rs.getString("aptitud"));
        c.setRecomendacion(rs.getString("recomendacion"));
        c.setObservaciones(rs.getString("observaciones"));
        c.setFechaEmision(rs.getDate("f_emision").toLocalDate());
        c.setFechaVenc(rs.getDate("f_venc").toLocalDate());
        c.setIdPersona(rs.getInt("idPersona"));
        c.setIdMedicoLaboral(rs.getInt("idMedicoLaboral"));
        c.setNombreMedico(rs.getString("nombreMedico") + " " + rs.getString("apellidoMedico"));
        return c;
    }
    public CertifMed buscarPorId(int idCertifMed) {
        String sql = "SELECT c.*, m.nombre AS nombreMedico, m.apellido AS apellidoMedico " +
                    "FROM Certif_Med c JOIN MedicoLaboral m ON c.idMedicoLaboral = m.idMedicoLaboral " +
                    "WHERE c.idCertif_Med = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCertifMed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
