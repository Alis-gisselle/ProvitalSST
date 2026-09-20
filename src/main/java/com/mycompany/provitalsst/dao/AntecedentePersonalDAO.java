/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.AntecedentePersonal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alis
 */
public class AntecedentePersonalDAO {

    public boolean insertar(AntecedentePersonal a) {
        String sql = "INSERT INTO antecedentepersonal (idFicha, enfermedades, cirugias, medicamentos, alergias, otrosRelevantes, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdFicha());
            ps.setBoolean(2, a.isEnfermedades());
            ps.setBoolean(3, a.isCirugias());
            ps.setBoolean(4, a.isMedicamentos());
            ps.setBoolean(5, a.isAlergias());
            ps.setBoolean(6, a.isOtrosRelevantes());
            ps.setString(7, a.getObservaciones());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean actualizar(AntecedentePersonal a) {
        String sql = "UPDATE antecedentepersonal SET enfermedades=?, cirugias=?, medicamentos=?, alergias=?, otrosRelevantes=?, observaciones=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, a.isEnfermedades());
            ps.setBoolean(2, a.isCirugias());
            ps.setBoolean(3, a.isMedicamentos());
            ps.setBoolean(4, a.isAlergias());
            ps.setBoolean(5, a.isOtrosRelevantes());
            ps.setString(6, a.getObservaciones());
            ps.setInt(7, a.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public AntecedentePersonal buscarPorId(int idFicha) {
        String sql = "SELECT * FROM antecedentepersonal WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AntecedentePersonal a = new AntecedentePersonal();
                a.setIdFicha(rs.getInt("idFicha"));
                a.setEnfermedades(rs.getBoolean("enfermedades"));
                a.setCirugias(rs.getBoolean("cirugias"));
                a.setMedicamentos(rs.getBoolean("medicamentos"));
                a.setAlergias(rs.getBoolean("alergias"));
                a.setOtrosRelevantes(rs.getBoolean("otrosRelevantes"));
                a.setObservaciones(rs.getString("observaciones"));
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
