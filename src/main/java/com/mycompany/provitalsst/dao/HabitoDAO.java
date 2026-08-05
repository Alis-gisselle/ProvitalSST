/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Habito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alis
 */
public class HabitoDAO {

    public boolean insertar(Habito h) {
        String sql = "INSERT INTO Habito (idFicha, fuma, consumeAlcohol, actividadFisica, suenoAdecuado, otrosHabitos, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdFicha());
            ps.setBoolean(2, h.isFuma());
            ps.setBoolean(3, h.isConsumeAlcohol());
            ps.setBoolean(4, h.isActividadFisica());
            ps.setBoolean(5, h.isSuenoAdecuado());
            ps.setBoolean(6, h.isOtrosHabitos());
            ps.setString(7, h.getObservaciones());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Habito buscarPorId(int idFicha) {
        String sql = "SELECT * FROM Habito WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Habito h = new Habito();
                h.setIdFicha(rs.getInt("idFicha"));
                h.setFuma(rs.getBoolean("fuma"));
                h.setConsumeAlcohol(rs.getBoolean("consumeAlcohol"));
                h.setActividadFisica(rs.getBoolean("actividadFisica"));
                h.setSuenoAdecuado(rs.getBoolean("suenoAdecuado"));
                h.setOtrosHabitos(rs.getBoolean("otrosHabitos"));
                h.setObservaciones(rs.getString("observaciones"));
                return h;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Habito h) {
        String sql = "UPDATE Habito SET fuma=?, consumeAlcohol=?, actividadFisica=?, suenoAdecuado=?, otrosHabitos=?, observaciones=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, h.isFuma());
            ps.setBoolean(2, h.isConsumeAlcohol());
            ps.setBoolean(3, h.isActividadFisica());
            ps.setBoolean(4, h.isSuenoAdecuado());
            ps.setBoolean(5, h.isOtrosHabitos());
            ps.setString(6, h.getObservaciones());
            ps.setInt(7, h.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
