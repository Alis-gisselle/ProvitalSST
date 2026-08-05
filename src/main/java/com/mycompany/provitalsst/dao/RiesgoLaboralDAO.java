/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.RiesgoLaboral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */
public class RiesgoLaboralDAO {

    public boolean insertar(RiesgoLaboral r) {
        String sql = "INSERT INTO RiesgoLaboral (idFicha, factor, expuesto, tiempoExposicion) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getIdFicha());
            ps.setString(2, r.getFactor());
            ps.setBoolean(3, r.isExpuesto());
            ps.setString(4, r.getTiempoExposicion());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void eliminarPorFicha(int idFicha) {
        String sql = "DELETE FROM RiesgoLaboral WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<RiesgoLaboral> listarPorFicha(int idFicha) {
        List<RiesgoLaboral> lista = new ArrayList<>();
        String sql = "SELECT * FROM RiesgoLaboral WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RiesgoLaboral r = new RiesgoLaboral();
                r.setIdRiesgo(rs.getInt("idRiesgo"));
                r.setIdFicha(rs.getInt("idFicha"));
                r.setFactor(rs.getString("factor"));
                r.setExpuesto(rs.getBoolean("expuesto"));
                r.setTiempoExposicion(rs.getString("tiempoExposicion"));
                lista.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
