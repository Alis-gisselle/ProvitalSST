/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.EPP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alis
 */
public class EPPDAO {

    public boolean insertar(EPP e) {
        String sql = "INSERT INTO epp (idFicha, tapaboca, proteccionAuditiva, casco, gafas, botas, guantes, delantal, otros) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getIdFicha());
            ps.setBoolean(2, e.isTapaboca());
            ps.setBoolean(3, e.isProteccionAuditiva());
            ps.setBoolean(4, e.isCasco());
            ps.setBoolean(5, e.isGafas());
            ps.setBoolean(6, e.isBotas());
            ps.setBoolean(7, e.isGuantes());
            ps.setBoolean(8, e.isDelantal());
            ps.setString(9, e.getOtros());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public EPP buscarPorId(int idFicha) {
        String sql = "SELECT * FROM epp WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EPP e = new EPP();
                e.setIdFicha(rs.getInt("idFicha"));
                e.setTapaboca(rs.getBoolean("tapaboca"));
                e.setProteccionAuditiva(rs.getBoolean("proteccionAuditiva"));
                e.setCasco(rs.getBoolean("casco"));
                e.setGafas(rs.getBoolean("gafas"));
                e.setBotas(rs.getBoolean("botas"));
                e.setGuantes(rs.getBoolean("guantes"));
                e.setDelantal(rs.getBoolean("delantal"));
                e.setOtros(rs.getString("otros"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(EPP e) {
        String sql = "UPDATE epp SET tapaboca=?, proteccionAuditiva=?, casco=?, gafas=?, botas=?, guantes=?, delantal=?, otros=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, e.isTapaboca());
            ps.setBoolean(2, e.isProteccionAuditiva());
            ps.setBoolean(3, e.isCasco());
            ps.setBoolean(4, e.isGafas());
            ps.setBoolean(5, e.isBotas());
            ps.setBoolean(6, e.isGuantes());
            ps.setBoolean(7, e.isDelantal());
            ps.setString(8, e.getOtros());
            ps.setInt(9, e.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}