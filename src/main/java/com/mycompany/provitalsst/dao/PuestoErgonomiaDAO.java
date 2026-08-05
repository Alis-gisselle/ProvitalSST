/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.PuestoErgonomia;
import java.math.BigDecimal;
import java.sql.*;

/**
 *
 * @author alis
 */
public class PuestoErgonomiaDAO {

    public boolean insertar(PuestoErgonomia p) {
        String sql = "INSERT INTO PuestoErgonomia (idFicha, descripcionGeneral, tareasPrincipales, tipoActividad, esfuerzoFisico, levantaCargas, pesoAprox) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdFicha());
            ps.setString(2, p.getDescripcionGeneral());
            ps.setString(3, p.getTareasPrincipales());
            ps.setString(4, p.getTipoActividad());
            ps.setString(5, p.getEsfuerzoFisico());
            ps.setBoolean(6, p.isLevantaCargas());
            if (p.getPesoAprox() != null) ps.setBigDecimal(7, p.getPesoAprox()); else ps.setNull(7, Types.DECIMAL);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean actualizar(PuestoErgonomia p) {
        String sql = "UPDATE PuestoErgonomia SET descripcionGeneral=?, tareasPrincipales=?, tipoActividad=?, esfuerzoFisico=?, levantaCargas=?, pesoAprox=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcionGeneral());
            ps.setString(2, p.getTareasPrincipales());
            ps.setString(3, p.getTipoActividad());
            ps.setString(4, p.getEsfuerzoFisico());
            ps.setBoolean(5, p.isLevantaCargas());
            if (p.getPesoAprox() != null) 
                ps.setBigDecimal(6, p.getPesoAprox()); 
            else 
                ps.setNull(6, Types.DECIMAL);
            ps.setInt(7, p.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public PuestoErgonomia buscarPorId(int idFicha) {
        String sql = "SELECT * FROM PuestoErgonomia WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PuestoErgonomia p = new PuestoErgonomia();
                p.setIdFicha(rs.getInt("idFicha"));
                p.setDescripcionGeneral(rs.getString("descripcionGeneral"));
                p.setTareasPrincipales(rs.getString("tareasPrincipales"));
                p.setTipoActividad(rs.getString("tipoActividad"));
                p.setEsfuerzoFisico(rs.getString("esfuerzoFisico"));
                p.setLevantaCargas(rs.getBoolean("levantaCargas"));
                p.setPesoAprox(rs.getBigDecimal("pesoAprox"));
                return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
