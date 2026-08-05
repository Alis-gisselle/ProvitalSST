/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Estudio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */

public class EstudioDAO {

    public boolean insertar(Estudio e) {
        String sql = "INSERT INTO Estudio (fecha, archivoPdf, idPersona, idMedicoLaboral) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(e.getFecha()));
            ps.setString(2, e.getArchivoPdf());
            ps.setInt(3, e.getIdPersona());
            ps.setInt(4, e.getIdMedicoLaboral());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Estudio> listarPorPersona(int idPersona) {
        List<Estudio> lista = new ArrayList<>();
        String sql = "SELECT e.*, m.nombre AS nombreMedico, m.apellido AS apellidoMedico " +
                     "FROM Estudio e JOIN MedicoLaboral m ON e.idMedicoLaboral = m.idMedicoLaboral " +
                     "WHERE e.idPersona = ? ORDER BY e.fecha DESC";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudio est = new Estudio();
                est.setIdEstudio(rs.getInt("idEstudio"));
                est.setFecha(rs.getDate("fecha").toLocalDate());
                est.setArchivoPdf(rs.getString("archivoPdf"));
                est.setIdPersona(rs.getInt("idPersona"));
                est.setIdMedicoLaboral(rs.getInt("idMedicoLaboral"));
                est.setNombreMedico(rs.getString("nombreMedico") + " " + rs.getString("apellidoMedico"));
                lista.add(est);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    public Estudio buscarPorId(int idEstudio) {
        String sql = "SELECT * FROM Estudio WHERE idEstudio = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEstudio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Estudio est = new Estudio();
                est.setIdEstudio(rs.getInt("idEstudio"));
                est.setFecha(rs.getDate("fecha").toLocalDate());
                est.setArchivoPdf(rs.getString("archivoPdf"));
                est.setIdPersona(rs.getInt("idPersona"));
                est.setIdMedicoLaboral(rs.getInt("idMedicoLaboral"));
                return est;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean eliminar(int idEstudio) {
        String sql = "DELETE FROM Estudio WHERE idEstudio = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEstudio);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
