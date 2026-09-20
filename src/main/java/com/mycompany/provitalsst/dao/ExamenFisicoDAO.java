/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.ExamenFisico;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author alis
 */
public class ExamenFisicoDAO {

    public boolean insertar(ExamenFisico e) {
        String sql = "INSERT INTO examenfisico (idFicha, peso, estatura, presionArterial, frecuenciaCardiaca, agudezaVisualDerecho, agudezaVisualIzquierdo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getIdFicha());
            setDecimalONull(ps, 2, e.getPeso());
            setDecimalONull(ps, 3, e.getEstatura());
            ps.setString(4, e.getPresionArterial());
            if (e.getFrecuenciaCardiaca() != null) ps.setInt(5, e.getFrecuenciaCardiaca()); else ps.setNull(5, Types.INTEGER);
            ps.setString(6, e.getAgudezaVisualDerecho());
            ps.setString(7, e.getAgudezaVisualIzquierdo());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(ExamenFisico e) {
        String sql = "UPDATE examenfisico SET peso=?, estatura=?, presionArterial=?, frecuenciaCardiaca=?, agudezaVisualDerecho=?, agudezaVisualIzquierdo=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setDecimalONull(ps, 1, e.getPeso());
            setDecimalONull(ps, 2, e.getEstatura());
            ps.setString(3, e.getPresionArterial());
            if (e.getFrecuenciaCardiaca() != null) ps.setInt(4, e.getFrecuenciaCardiaca()); else ps.setNull(4, Types.INTEGER);
            ps.setString(5, e.getAgudezaVisualDerecho());
            ps.setString(6, e.getAgudezaVisualIzquierdo());
            ps.setInt(7, e.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ExamenFisico buscarPorId(int idFicha) {
        String sql = "SELECT * FROM examenfisico WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ExamenFisico e = new ExamenFisico();
                e.setIdFicha(rs.getInt("idFicha"));
                e.setPeso(rs.getBigDecimal("peso"));
                e.setEstatura(rs.getBigDecimal("estatura"));
                e.setPresionArterial(rs.getString("presionArterial"));
                e.setFrecuenciaCardiaca(rs.getObject("frecuenciaCardiaca") != null ? rs.getInt("frecuenciaCardiaca") : null);
                e.setAgudezaVisualDerecho(rs.getString("agudezaVisualDerecho"));
                e.setAgudezaVisualIzquierdo(rs.getString("agudezaVisualIzquierdo"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void setDecimalONull(PreparedStatement ps, int index, BigDecimal valor) throws SQLException {
        if (valor != null) ps.setBigDecimal(index, valor); else ps.setNull(index, Types.DECIMAL);
    }
}