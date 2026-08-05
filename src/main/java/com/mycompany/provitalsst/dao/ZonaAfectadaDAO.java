/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.ZonaAfectada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */
public class ZonaAfectadaDAO {

    public boolean insertar(ZonaAfectada z) {
        String sql = "INSERT INTO ZonaAfectada (idFicha, zona, intensidad) VALUES (?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, z.getIdFicha());
            ps.setString(2, z.getZona());
            if (z.getIntensidad() != null) ps.setInt(3, z.getIntensidad()); else ps.setNull(3, Types.INTEGER);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Borra todas las zonas de esta ficha (para volver a insertarlas al editar)
    public void eliminarPorFicha(int idFicha) {
        String sql = "DELETE FROM ZonaAfectada WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<ZonaAfectada> listarPorFicha(int idFicha) {
        List<ZonaAfectada> lista = new ArrayList<>();
        String sql = "SELECT * FROM ZonaAfectada WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ZonaAfectada z = new ZonaAfectada();
                z.setIdZona(rs.getInt("idZona"));
                z.setIdFicha(rs.getInt("idFicha"));
                z.setZona(rs.getString("zona"));
                z.setIntensidad(rs.getObject("intensidad") != null ? rs.getInt("intensidad") : null);
                lista.add(z);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
