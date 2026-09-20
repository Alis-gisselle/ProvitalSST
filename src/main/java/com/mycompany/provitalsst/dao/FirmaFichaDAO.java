/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.FirmaFicha;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author alis
 */
public class FirmaFichaDAO {

    public boolean insertar(FirmaFicha f) {
        String sql = "INSERT INTO firmaficha (idFicha, ciTrabajador, fechaFirmaTrabajador, ciTecnico, fechaFirmaTecnico) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, f.getIdFicha());
            ps.setString(2, f.getCiTrabajador());
            setDateONull(ps, 3, f.getFechaFirmaTrabajador());
            ps.setString(4, f.getCiTecnico());
            setDateONull(ps, 5, f.getFechaFirmaTecnico());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(FirmaFicha f) {
        String sql = "UPDATE firmaficha SET ciTrabajador=?, fechaFirmaTrabajador=?, ciTecnico=?, fechaFirmaTecnico=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getCiTrabajador());
            setDateONull(ps, 2, f.getFechaFirmaTrabajador());
            ps.setString(3, f.getCiTecnico());
            setDateONull(ps, 4, f.getFechaFirmaTecnico());
            ps.setInt(5, f.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public FirmaFicha buscarPorId(int idFicha) {
        String sql = "SELECT * FROM firmaficha WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                FirmaFicha f = new FirmaFicha();
                f.setIdFicha(rs.getInt("idFicha"));
                f.setCiTrabajador(rs.getString("ciTrabajador"));
                if (rs.getDate("fechaFirmaTrabajador") != null) f.setFechaFirmaTrabajador(rs.getDate("fechaFirmaTrabajador").toLocalDate());
                f.setCiTecnico(rs.getString("ciTecnico"));
                if (rs.getDate("fechaFirmaTecnico") != null) f.setFechaFirmaTecnico(rs.getDate("fechaFirmaTecnico").toLocalDate());
                return f;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void setDateONull(PreparedStatement ps, int index, java.time.LocalDate fecha) throws SQLException {
        if (fecha != null) ps.setDate(index, Date.valueOf(fecha)); else ps.setNull(index, Types.DATE);
    }
}
